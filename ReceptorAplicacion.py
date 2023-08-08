class ReceptorAplicacion(object):

    def show_message(self, message, type, error=None):
        if type == 2 and error: # Hamming es 2 y CRC 1
            print(f"Mensaje arreglado: {message}")
            print(f"Errores: {error}")
        if error:
            print(f"Errores: {error}")
        else:
            print(f"Mensaje: {message}")