# MercadoLibre Clon
Una pequeña Android App que permite visualizar un listado de productos obtenidos desde las APIs abiertas de Mercado Libre, como así también, poder visualizar el detalle de cada producto. 
Esta aplicación fue construida siguiendo los lineamientos de Clean Architecture, el patrón de diseño MVVM y los guidelines provistos para la plataforma Android. 


## Dependencias integradas al proyecto.

### Fast Android Networking:
Fast Android Networking Library es una biblioteca poderosa para hacer cualquier tipo de red en aplicaciones de Android que se realiza sobre la capa de red OkHttp.

Nota: Considero que es una librería con mayor número de funcionalidades y con una sintaxis mucho más amigable y limpia (en términos de código) en comparación con Retrofit (La utilice en mis primeros proyectos).

Mas info: https://github.com/amitshekhariitbhu/Fast-Android-Networking

### RxJava: Extensiones reactivas para la JVM
RxJava es una implementación Java VM de Reactive Extensions : una biblioteca para componer programas asincrónicos y basados en eventos mediante el uso de secuencias observables.

Extiende el patrón de observador para admitir secuencias de datos/eventos y agrega operadores que le permiten componer secuencias de manera declarativa, al tiempo que abstrae preocupaciones sobre cosas como subprocesos de bajo nivel, sincronización, seguridad de subprocesos y estructuras de datos concurrentes.

Mas info: https://github.com/ReactiveX/RxJava

### RxAndroid: Extensiones reactivas para Android
Enlaces específicos de Android para RxJava 2 .

Este módulo agrega las clases mínimas a RxJava que hacen que la escritura de componentes reactivos en aplicaciones de Android sea fácil y sin complicaciones. Más específicamente, proporciona un Scheduler que programa en el hilo principal o en cualquier otro Looper.

Mas info: https://github.com/ReactiveX/RxAndroid

### Butter Knife
Enlace de campo y método para vistas de Android que utiliza el procesamiento de anotaciones para generar código repetitivo para usted.

  - Eliminar findViewById llamadas mediante el uso @BindView en los campos.
  - Agrupe varias vistas en una lista o matriz. Opere en todos ellos a la vez con acciones, establecedores o propiedades.
  - Elimine las clases internas anónimas para los métodos listener con la anotación @OnClick y otros.
  - Elimine las búsquedas de recursos mediante el uso de anotaciones de recursos en los campos.

Mas info: https://github.com/JakeWharton/butterknife

### Dagger 2
Un inyector de dependencia rápido para Java y Android.

Dagger es un marco de tiempo de compilación para la inyección de dependencias. No utiliza reflexión ni generación de bytecode en tiempo de ejecución, realiza todo su análisis en tiempo de compilación y genera código fuente Java simple.

Mas info: https://github.com/google/dagger

### Glide
Glide es un marco de carga de imágenes y gestión de medios de código abierto rápido y eficiente para Android que envuelve la decodificación de medios, el almacenamiento en caché de memoria y disco y la agrupación de recursos en una interfaz simple y fácil de usar.

Mas info: https://github.com/bumptech/glide

### MaterialDesignIcons
Libreria que importa todos los dibujos vectoriales de materialdesignicons.com a su R.drawable/

Mas info: https://github.com/MrBIMC/MaterialDesignIcons

### Toasty
Librería que proporciona Toast personalizados.

Mas info: https://github.com/GrenderG/Toasty
