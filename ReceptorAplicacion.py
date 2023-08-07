class ReceptorAplicacion(object):

    def show_message(self, message, error=None):
        if error:
            print(f"Errores: {error}")
        else:
            print(f"Mensaje: {message}")