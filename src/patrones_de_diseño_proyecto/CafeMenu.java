package patrones_de_diseño_proyecto;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CafeMenu extends JFrame {
    private static final long serialVersionUID = 1L;
    private final Color VERDE = new Color(24, 68, 52);
    private final Color CREMA = new Color(246, 242, 232);
    private final Color BLANCO = new Color(255, 255, 252);
    private final Color CAFE = new Color(126, 86, 59);
    private final Color TEXTO = new Color(48, 55, 49);

    private final List<Bebida> carrito = new ArrayList<>();
    private JPanel panelCarrito;
    private JLabel totalLabel, cantidadLabel;

    public CafeMenu() {
        setTitle("Coffee House | Punto de venta");
        setSize(1200, 780);
        setMinimumSize(new Dimension(980, 650));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(CREMA);
        setLayout(new BorderLayout(0, 0));
        crearEncabezado();
        crearMenu();
        crearPanelPedido();
        setVisible(true);
    }

    private JButton estilizarBoton(String texto, Color fondo, Color letra) {
        JButton b = new JButton(texto);
        b.setBackground(fondo);
        b.setForeground(letra);
        b.setFont(new Font("SansSerif", Font.BOLD, 13));
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createEmptyBorder(11, 14, 11, 14));
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b.setOpaque(true);
        b.setBorderPainted(false);
        return b;
    }

    private void crearEncabezado() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(VERDE);
        header.setBorder(BorderFactory.createEmptyBorder(24, 30, 22, 30));

        JLabel titulo = new JLabel("COFFEE HOUSE");
        titulo.setForeground(BLANCO);
        titulo.setFont(new Font("Serif", Font.BOLD, 34));

        JLabel subtitulo = new JLabel("CAFÉ DE ESPECIALIDAD  ·  TU MOMENTO FAVORITO");
        subtitulo.setForeground(new Color(218, 230, 219));
        subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 12));

        JPanel textos = new JPanel();
        textos.setOpaque(false);
        textos.setLayout(new BoxLayout(textos, BoxLayout.Y_AXIS));
        textos.add(titulo);
        textos.add(Box.createVerticalStrut(6));
        textos.add(subtitulo);

        JLabel sello = new JLabel("  ☕  ");
        sello.setForeground(new Color(237, 224, 197));
        sello.setFont(new Font("Serif", Font.PLAIN, 36));
        header.add(textos, BorderLayout.WEST);
        header.add(sello, BorderLayout.EAST);
        add(header, BorderLayout.NORTH);
    }

    private void crearMenu() {
        JPanel catalogo = new JPanel(new GridLayout(0, 2, 16, 16));
        catalogo.setBackground(CREMA);
        catalogo.setBorder(BorderFactory.createEmptyBorder(14, 18, 18, 18));

        // USO DE FACTORY METHOD PARA POBLAR EL CATÁLOGO
        for (String nombre : BebidaFactory.getCatalogoNombres()) {
            Bebida bebida = BebidaFactory.crearBebida(nombre);
            catalogo.add(crearTarjeta(bebida));
        }

        JScrollPane scroll = new JScrollPane(catalogo);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(CREMA);
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        JPanel contenido = new JPanel(new BorderLayout());
        contenido.setBackground(CREMA);
        contenido.setBorder(BorderFactory.createEmptyBorder(8, 18, 10, 10));
        JLabel titulo = new JLabel("  NUESTRAS BEBIDAS");
        titulo.setForeground(VERDE);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 21));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 4, 8, 4));
        contenido.add(titulo, BorderLayout.NORTH);
        contenido.add(scroll, BorderLayout.CENTER);
        add(contenido, BorderLayout.CENTER);
    }

    private JPanel crearTarjeta(Bebida bebida) {
        JPanel tarjeta = new JPanel(new BorderLayout(8, 10));
        tarjeta.setBackground(BLANCO);
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(226, 220, 208), 1),
                BorderFactory.createEmptyBorder(16, 17, 16, 17)));

        JLabel icono = new JLabel(bebida.getIcono(), SwingConstants.CENTER);
        icono.setFont(new Font("SansSerif", Font.PLAIN, 36));
        icono.setForeground(CAFE);
        icono.setBorder(BorderFactory.createEmptyBorder(2, 0, 4, 0));

        JLabel nombreLabel = new JLabel(bebida.getNombre());
        nombreLabel.setForeground(VERDE);
        nombreLabel.setFont(new Font("SansSerif", Font.BOLD, 16));

        JLabel descripcion = new JLabel(bebida.getDescripcion());
        descripcion.setForeground(new Color(120, 116, 106));
        descripcion.setFont(new Font("SansSerif", Font.PLAIN, 11));

        JLabel precioLabel = new JLabel(String.format("$%.2f MXN", bebida.getPrecio()));
        precioLabel.setForeground(CAFE);
        precioLabel.setFont(new Font("SansSerif", Font.BOLD, 14));

        JPanel detalles = new JPanel();
        detalles.setOpaque(false);
        detalles.setLayout(new BoxLayout(detalles, BoxLayout.Y_AXIS));
        detalles.add(nombreLabel);
        detalles.add(Box.createVerticalStrut(4));
        detalles.add(descripcion);
        detalles.add(Box.createVerticalStrut(8));
        detalles.add(precioLabel);

        JButton agregar = estilizarBoton("＋  AGREGAR AL PEDIDO", VERDE, BLANCO);
        agregar.addActionListener(e -> agregarProducto(bebida.getNombre()));

        tarjeta.add(icono, BorderLayout.NORTH);
        tarjeta.add(detalles, BorderLayout.CENTER);
        tarjeta.add(agregar, BorderLayout.SOUTH);
        return tarjeta;
    }

    private void crearPanelPedido() {
        panelCarrito = new JPanel();
        panelCarrito.setLayout(new BoxLayout(panelCarrito, BoxLayout.Y_AXIS));
        panelCarrito.setBackground(BLANCO);

        JScrollPane scroll = new JScrollPane(panelCarrito);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(BLANCO);

        JPanel pedido = new JPanel(new BorderLayout(12, 12));
        pedido.setPreferredSize(new Dimension(330, 0));
        pedido.setBackground(BLANCO);
        pedido.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(226, 220, 208)),
                BorderFactory.createEmptyBorder(22, 20, 20, 20)));

        JLabel titulo = new JLabel("TU PEDIDO");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        titulo.setForeground(VERDE);
        cantidadLabel = new JLabel("0 bebidas seleccionadas");
        cantidadLabel.setForeground(CAFE);
        cantidadLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));

        JPanel arriba = new JPanel();
        arriba.setOpaque(false);
        arriba.setLayout(new BoxLayout(arriba, BoxLayout.Y_AXIS));
        arriba.add(titulo);
        arriba.add(Box.createVerticalStrut(5));
        arriba.add(cantidadLabel);

        totalLabel = new JLabel("TOTAL  $0.00 MXN");
        totalLabel.setFont(new Font("SansSerif", Font.BOLD, 21));
        totalLabel.setForeground(VERDE);
        totalLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));

        JButton quitar = estilizarBoton("Quitar producto", new Color(235, 231, 222), TEXTO);
        JButton limpiar = estilizarBoton("Limpiar pedido", new Color(235, 231, 222), TEXTO);
        JButton cobrar = estilizarBoton("FINALIZAR COMPRA", VERDE, BLANCO);
        quitar.addActionListener(e -> quitarProducto());
        limpiar.addActionListener(e -> limpiarPedido());
        cobrar.addActionListener(e -> cobrar());

        JPanel botones = new JPanel(new GridLayout(3, 1, 8, 8));
        botones.setOpaque(false);
        botones.add(quitar);
        botones.add(limpiar);
        botones.add(cobrar);

        JPanel abajo = new JPanel(new BorderLayout(8, 8));
        abajo.setOpaque(false);
        abajo.add(totalLabel, BorderLayout.NORTH);
        abajo.add(botones, BorderLayout.CENTER);

        pedido.add(arriba, BorderLayout.NORTH);
        pedido.add(scroll, BorderLayout.CENTER);
        pedido.add(abajo, BorderLayout.SOUTH);
        add(pedido, BorderLayout.EAST);
    }

    private void agregarProducto(String nombreBase) {
        // 1. Creamos la bebida base con Factory Method
        Bebida bebida = BebidaFactory.crearBebida(nombreBase);

        // 2. Aplicamos Decorator si el usuario desea personalizarla
        String[] extras = {
            "Sin extras",
            "Extra Shot (+$15.00)",
            "Leche Deslactosada (+$10.00)",
            "Crema Batida (+$12.00)"
        };
        String seleccion = (String) JOptionPane.showInputDialog(this,
                "¿Deseas agregar algún extra a tu " + nombreBase + "?",
                "Personalizar bebida", JOptionPane.QUESTION_MESSAGE, null, extras, extras[0]);

        if (seleccion == null) return; // Canceló

        if (seleccion.contains("Extra Shot")) {
            bebida = new ExtraShot(bebida);
        } else if (seleccion.contains("Deslactosada")) {
            bebida = new LecheDeslactosada(bebida);
        } else if (seleccion.contains("Crema Batida")) {
            bebida = new CremaBatida(bebida);
        }

        carrito.add(bebida);
        actualizarCarrito();
    }

    private void quitarProducto() {
        if (carrito.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El pedido está vacío.");
            return;
        }
        String[] opciones = new String[carrito.size()];
        for (int i = 0; i < carrito.size(); i++) {
            opciones[i] = (i + 1) + ". " + carrito.get(i).getNombre();
        }
        String seleccionado = (String) JOptionPane.showInputDialog(this,
                "Selecciona el producto que deseas quitar:", "Quitar producto",
                JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
        if (seleccionado == null) return;

        int indice = Integer.parseInt(seleccionado.split("\\.")[0]) - 1;
        carrito.remove(indice);
        actualizarCarrito();
    }

    private void limpiarPedido() {
        carrito.clear();
        actualizarCarrito();
    }

    private void actualizarCarrito() {
        panelCarrito.removeAll();
        double total = 0;
        for (Bebida b : carrito) {
            total += b.getPrecio();

            JPanel linea = new JPanel(new BorderLayout(5, 5));
            linea.setBackground(BLANCO);
            linea.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,
                    new Color(232, 228, 219)));
            JLabel producto = new JLabel("<html><b>" + b.getNombre() + "</b></html>");
            producto.setForeground(TEXTO);
            producto.setBorder(BorderFactory.createEmptyBorder(12, 5, 12, 5));
            JLabel subtotal = new JLabel("$" + String.format("%.2f", b.getPrecio()));
            subtotal.setForeground(CAFE);
            subtotal.setFont(new Font("SansSerif", Font.BOLD, 13));
            linea.add(producto, BorderLayout.CENTER);
            linea.add(subtotal, BorderLayout.EAST);
            linea.setMaximumSize(new Dimension(Integer.MAX_VALUE, 55));
            panelCarrito.add(linea);
        }
        cantidadLabel.setText(carrito.size() + (carrito.size() == 1 ? " bebida seleccionada" : " bebidas seleccionadas"));
        totalLabel.setText("TOTAL  $" + String.format("%.2f", total) + " MXN");
        panelCarrito.revalidate();
        panelCarrito.repaint();
    }

    private void cobrar() {
        if (carrito.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El pedido está vacío.");
            return;
        }

        double subtotal = 0;
        for (Bebida b : carrito) subtotal += b.getPrecio();

        // USO DE STRATEGY PARA EL MÉTODO DE PAGO
        String[] metodos = {"Efectivo (5% de descuento)", "Tarjeta de Crédito/Débito"};
        int opcion = JOptionPane.showOptionDialog(this,
                "Selecciona tu método de pago:", "Método de Pago",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, metodos, metodos[0]);

        if (opcion == -1) return;

        EstrategiaPago estrategia = (opcion == 0) ? new PagoEfectivo() : new PagoTarjeta();

        JOptionPane.showMessageDialog(this,
                "¡Gracias por tu compra! ☕\n\n" + estrategia.getDetallePago(subtotal),
                "Compra finalizada", JOptionPane.INFORMATION_MESSAGE);
        limpiarPedido();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CafeMenu());
    }
}