from ReceptorAplicacion import ReceptorAplicacion

class ReceptorPresentacion(object):

    def bin_to_ascii(self, binary_string):
        binary_list = [binary_string[i:i+7] for i in range(0, len(binary_string), 7)]  # Divide la cadena en bloques de 8 bits
        decoded_text = ""

        for binary in binary_list:
            decimal_value = int(binary, 2)  # Convierte el valor binario a decimal
            char = chr(decimal_value)  # Obtiene el carácter ASCII correspondiente
            decoded_text += char

        return decoded_text
    
    def decode_message(self, message, error=None):
        aplicacion = ReceptorAplicacion()
        if error:
            aplicacion.show_message(message, error)
        else:
            message = self.bin_to_ascii(message)
            aplicacion.show_message(message)