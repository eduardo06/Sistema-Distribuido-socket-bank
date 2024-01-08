import tkinter as tk

ventanaProducto = tk.Tk()
ventanaProducto.title('Producto')
ventanaProducto.geometry('300x400')

labelProducto = tk.Label(ventanaProducto, text = "Nombre del producto:")
labelProducto.place(x=10,y=10)
entradaProducto = tk.Entry(ventanaProducto, font=("Calibri 12"), width=40)
entradaProducto.place(x=170,y=10)

labelProdPresent = tk.Label(ventanaProducto, text = "Presentacion")
entradaProdPresent = tk.Entry(ventanaProducto)



def kardek():
    print ("hola")

boton1 = tk.Button(ventanaProducto, text = "Ingresar", command = lambda: kardek())


ventanaProducto.mainloop()