 Prompt1: Tengo una app Compose de reservas de clases de gimnasio. Mi modelo Reservasolo tiene los estados Confirmada y Completada. Quiero que el usuario pueda cancelar una reserva confirmada. Cual es la forma mas simple de agregar un estado de cancelacion al enum EstadoReserva sin usar ViewModel?
 Prompt2: Usando Compose Material3, quiero mostrar un AlertDialog de confirmacion antes de cancelar una reserva en ReservasScreen. Debe indicar el nombre de la clase y el horario, con un boton de confirmar 'Si, cancelar' y otro 'Volver'. La función onCancelar debe recibir la Reserva seleccionada.
 Prompt3: Ajusta el diseño visual de mi app (TECSUP Fit) para que coincida exactamente con este sistema de diseño:

 PALETA DE COLORES
 - Verde principal (headers, botones activos, iconos activos): #1B5E4F aprox (verde bosque oscuro)
 - Verde claro de fondo (badges, tarjetas de estado "confirmada"): #E8F5F0 con texto verde
 - Fondo general de pantalla: blanco (#FFFFFF)
 - Fondo de tarjetas secundarias/inactivas: gris muy claro #F2F2F2 o #F5F5F5
 - Texto principal: negro/gris oscuro #1A1A1A
 - Texto secundario (horarios, subtítulos): gris medio #7A7A7A
 - Bordes de tarjetas: gris claro #E0E0E0, radio de esquina grande (16px aprox)

 TIPOGRAFÍA
 - Sans-serif estilo system font (San Francisco / Inter / Roboto)
 - Títulos de pantalla: bold, 18-20px
 - Nombres de clases/items: semibold, 15-16px
 - Texto secundario/horarios: regular, 12-13px, color gris

 COMPONENTES CLAVE

 1. Header superior (verde sólido, esquinas inferiores redondeadas, texto blanco):
     - Saludo "Hola, [Nombre]"
     - Título de sección debajo

 2. Tabs tipo "pill" (Hoy / Esta semana):
     - Activo: fondo verde oscuro, texto blanco, bordes redondeados completos
     - Inactivo: fondo transparente/gris claro, texto gris

 3. Tarjetas de lista (clases, reservas):
     - Fondo blanco o gris muy claro
     - Icono circular a la izquierda (fondo verde claro, icono verde oscuro)
     - Título en negrita + subtítulo gris (hora, sala)
     - Borde sutil o sombra ligera
     - Esquinas redondeadas (12-16px)

 4. Estado/badge (ej. "Confirmada", "Completada"):
     - Texto pequeño en verde sobre fondo verde muy claro, forma de píldora

 5. Botón principal (ej. "Reservar cupo"):
     - Ancho completo, fondo verde oscuro sólido, texto blanco, bold
     - Esquinas redondeadas (12px), altura generosa (48-52px)

 6. Botón secundario (ej. "Ver mis reservas"):
     - Fondo gris claro, texto negro, mismas esquinas redondeadas

 7. Pantalla de confirmación:
     - Ícono circular grande con check verde sobre fondo verde clarito, centrado
     - Título bold debajo, subtítulo gris con detalle de la reserva

 8. Avatar de perfil:
     - Círculo verde oscuro con iniciales en blanco, centrado
     - Nombre bold debajo, plan/subtítulo en gris
     - Tarjetas de estadísticas (número grande bold + label gris) en fila, fondo gris claro, esquinas redondeadas

 9. Bottom Navigation Bar (4 tabs: Inicio, Reservas, Rutinas, Perfil):
     - Fondo blanco, borde superior sutil, esquinas superiores redondeadas
     - Tab activo: icono y texto en verde oscuro
     - Tabs inactivos: icono y texto en gris
     - Iconos outline simples, texto pequeño (10-11px) debajo del icono

 ESPACIADO Y LAYOUT
 - Padding lateral consistente de 16-20px en toda la pantalla
 - Separación entre tarjetas de 10-12px
 - Jerarquía visual clara: header → contenido con scroll → bottom nav fijo

 Aplica estos estilos manteniendo la estructura y funcionalidad de mi código actual, solo actualizando colores, tipografía, espaciados, bordes y componentes visuales para que coincidan con este diseño.