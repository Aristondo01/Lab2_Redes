import math
from ReceptorPresentacion import ReceptorPresentacion
import matplotlib.pyplot as plt


class Hamming(object):
    def __init__(self):
        self.Errores = 0
        self.Correctas = 0
        self.word_length={}
        
    def get_estadisticas(self):
        print("Accuracy: ", self.Correctas/(self.Correctas+self.Errores))
        
        x = self.word_length.keys()
        y = self.word_length.values()
        
        plt.bar(x, y)
        
        plt.xlabel('Longitud de palabra')
        plt.ylabel('Cantidad de errores encontrados')
        plt.title('Histograma de errores')
        plt.show(True)
        
    def push_diccionario(self,word):
        word_len = len(word)
        if word_len not in self.word_length:
            self.word_length[word_len] = 1
        else:
            self.word_length[word_len] += 1

        
    
    def rotate_trama(self):
        self.trama = self.trama[::-1]
        self.trama = [int(c) for c in self.trama]
        self.r_errors = {}

    def rotate(self, trama):
        return trama[::-1]

    def find_r(self):
        return math.floor(math.log2(len(self.trama))) + 1
    
    def get_positions_with_1(self, variable, num_variables):
        positions = []
        for i in range(2**num_variables):
            if (i >> variable) & 1:
                positions.append(i)
        return positions
    
    def base_2_to_10(self, num):
        result = 0
        i = 0
        for element in num:
            result += int(element) * 2**i
            i += 1
        return result
    
    def original_message(self):
        r = self.find_r()
        original = []
        for i in range(len(self.trama)):
            if i + 1 not in [2**j for j in range(r)]:
                original.append(self.trama[i])
        return self.rotate(original)
    
    def add_error_entry(self, r):
        if r not in self.r_errors:
            self.r_errors[r] = 1
        else:
            self.r_errors[r] += 1
        
    def correct_errors(self):
        r = self.find_r()
        errors = []
        result = ""
        for pow in range(r):
            if 2**pow >= len(self.trama):
                break
            positions = self.get_positions_with_1(pow, r)
            ones = 0
            for position in positions:
                if position <= len(self.trama):
                    ones += self.trama[position - 1]
            if ones % 2 != 0:
                errors.append(2**pow)
                result += "1"
            else:
                result += "0"
        
        if errors:
            self.add_error_entry(r)
            error_position = self.base_2_to_10(result)
            self.trama[error_position - 1] = 1 if self.trama[error_position - 1] == 0 else 0
            # self.rotate_trama()
            return error_position, "".join(str(element) for element in self.rotate(self.trama)), "".join(str(element) for element in self.original_message())
        
        # self.rotate_trama()
        return None, "".join(str(element) for element in self.rotate(self.trama)), "".join(str(element) for element in self.original_message())
    
    def detect_error(self, trama):
        self.trama = trama
        self.rotate_trama()
        error, corrected, original = self.correct_errors()
        presentacion = ReceptorPresentacion()
        if error:
            self.Errores += 1
            error_word = presentacion.decode_message(corrected, 2, error)
            self.push_diccionario(error_word)
            # print("Hubo errores en la trama en la posicion:", error)
            # print("Trama corregida:", corrected + ".", "Trama original:", trama)
        else:
            self.Correctas += 1
            presentacion.decode_message(original, 2)
            # print("No hubo errores en la trama:", corrected + ".", "Trama original:", original)

    def get_estadisticas(self):
        print("Accuracy: ", self.Correctas/(self.Correctas+self.Errores))
        
        x = self.word_length.keys()
        y = self.word_length.values()
        
        plt.bar(x, y)
        
        plt.xlabel('Longitud de palabra')
        plt.ylabel('Cantidad de errores encontrados')
        plt.title('Histograma de errores')
        plt.show()

        x = self.r_errors.keys()
        y = self.r_errors.values()
        plt.bar(x, y)
        
        plt.xlabel('Valor P de Hamming')
        plt.ylabel('Cantidad de errores encontrados')
        plt.title('Histograma de errores')
        plt.show()


# def verify_input(trama):
#     for element in trama:
#         if element != "0" and element != "1":
#             return False
        
#     return True

# print("Ingrese la trama")
# trama = input()
# if not verify_input(trama):
#     print("La trama es inválida")
# else:
#     hamming = Hamming(trama)
#     error, corrected, original = hamming.correct_errors()
#     if error:
#         print("Hubo errores en la trama en la posicion:", error)
#         print("Trama corregida:", corrected + ".", "Trama original:", trama)
#     else:
#         print("No hubo errores en la trama:", corrected + ".", "Trama original:", original)