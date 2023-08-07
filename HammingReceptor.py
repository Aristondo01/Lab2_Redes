import math
from ReceptorPresentacion import ReceptorPresentacion

class Hamming(object):
    def rotate_trama(self):
        self.trama = self.trama[::-1]
        self.trama = [int(c) for c in self.trama]

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
            presentacion.decode_message(corrected, error)
            # print("Hubo errores en la trama en la posicion:", error)
            # print("Trama corregida:", corrected + ".", "Trama original:", trama)
        else:
            presentacion.decode_message(original)
            # print("No hubo errores en la trama:", corrected + ".", "Trama original:", original)



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