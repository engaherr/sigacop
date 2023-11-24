[33mcommit 1cff309e7eaeef2991646ae16b0f0c143408ddd9[m[33m ([m[1;36mHEAD -> [m[1;32mcifrado-contraseñas[m[33m, [m[1;32mdevelopment[m[33m)[m
Author: engaherr <kikgamboa@gmail.com>
Date:   Fri Nov 24 00:56:57 2023 -0600

    feat(inicio de sesion y crud profesores): añadido cifrado de contraseñas y cambios en la logica de: lista profesores y registro/actualizacion profesores

[33mcommit d232727fa8a577d534805cd68e42a56a6d13e89f[m[33m ([m[1;31morigin/development[m[33m)[m
Author: rodrigo19112003 <131178299+rodrigo19112003@users.noreply.github.com>
Date:   Thu Nov 23 17:41:17 2023 -0600

    pr(development): Creacion de GUI y logica para historial de constancias y union del sistema completo
    
    * fix(VentanasSistema): correccion y agregacion de conexiones a las ventanas del sistema
    
    * feat(HistorialConstancias): Creacion de GUI para consultar el historial de constancias
    
    * feat(HistorialConstancias): Creacion de logica para consultar constancias para los tipos de usuario

[33mcommit ee0f75118b1be005af8c17b5a6fa321ad892d06f[m
Author: Ángel de Jesús de la Cruz García <82688573+adjcg15@users.noreply.github.com>
Date:   Tue Nov 21 22:34:31 2023 -0600

    pr(development): generación de constancias
    
    * feat(controlador y vista): interfaz gráfica de usuario para la generación de constancias e identificadores de elementos en controlador
    
    * feat(FXMLGeneracionConstanciasController): carga de experiencias, periodos y programas
    
    * feat(FXMLGeneracionConstanciasController): mostrado de seccion y bloque durante selección de experiencia educativa
    
    * feat(FXMLGeneracionConstanciasController): manejo de errores para campos del formulario vacíos
    
    * feat(FXMLGeneracionConstanciasController): estructura general para documento de constancia de experiencia docente
    
    * feat(LogicaGeneralCrudProfesores): Creacion de la logica para el registro de los profesores en el sistema
    
    * feat(validaciones.java): creacion del archivo para validar el formato de los campos para registrar profesor
    
    * feat(controlador  y vista): interfaz grafica de usuario para la consulta de profesores registrados
    
    * feat(interfaces): interfaces para la notificacion de operaciones en el crud de profesor
    
    * fix(crudProfesoresDAO): corrección de metodos existentes para solucionar errores
    
    * fix(FXMLCrudProfesoresController): corrección de lógica y agregación de métodos
    
    * fix(FXMLConsultarListaProfesores): agregar flecha para regresar
    
    * fix(crudProfesoresController): generar cuenta con la informacion del usuario
    
    * fix(CrudProfesoresDAO): correccion de la consulta para actualizar la informacion del profesor
    
    * feat(nbproject): configuración de Java 1.8 en rama de CRUD profesores
    
    * feat(FXMLGeneracionConstancias lógica): manejo de excepciones con archivos generados y selección dinámica de carpeta donde se guarda la constancia
    
    * feat(lógica de generación de constancias): guardado de constancias en base de datos y manejo de mostrado de mensajes en la generación
    
    ---------
    
    Co-authored-by: DonajiNavarro15 <ada_hack49@hackademy.mx>

[33mcommit 324fe2d266ada235144c9935ca196dc7b38cbbff[m
Author: Enrique Gamboa Hernández <113001293+engaherr@users.noreply.github.com>
Date:   Tue Nov 14 16:46:37 2023 -0600

    pr(development): menu principal con lógica añadido y singleton de login
    
    * feat(controlador y vista): Añadido el menu principal con la logica de cerrar sesion, cuenta singleton añadido para el inicio de sesion
    
    * feat(lógica de login): en el login ahora se settea todos los atributos del usuario que ingresa en el singleton

[33mcommit 0423008e53b3581d930987c4f2352b85b2c1702b[m[33m ([m[1;31morigin/master[m[33m, [m[1;32mmaster[m[33m)[m
Author: engaherr <kikgamboa@gmail.com>
Date:   Tue Nov 7 00:37:26 2023 -0600

    Commit inicial con inicio de sesion
