import random

def generar_palabra(longitud):
    consonantes = 'bcdfghjklmnpqrstvwxyz'
    vocales = 'aeiou'
    palabra = ''
    for i in range(longitud):
        if i % 2 == 0:
            palabra += random.choice(consonantes)
        else:
            palabra += random.choice(vocales)
    return palabra

def generar_palabras(cantidad, longitud):
    palabras = ""
    for i in range(cantidad):
        longitud_random = random.randint(1, longitud)
        palabras += generar_palabra(longitud_random) + "\n"
        
    with open("palabras.txt", "w") as archivo:
        archivo.write(palabras)
        

#a = input("Ingrese la cantidad de palabras a generar: ")
a= 100000
generar_palabras(int(a), 20)
    
    
