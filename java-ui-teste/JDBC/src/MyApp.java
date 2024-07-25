import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class MyApp extends JFrame implements ActionListener {

    private final JTextField inputTexto;
    private final JTextField inputNome;
    private final JTextArea messageArea;
    static final String url = "jdbc:mysql://localhost:3306/javadb";
    static final String user = "root";
    static final String password = "";

    public MyApp(){
        JLabel labelTexto = new JLabel();
        JLabel labelNome = new JLabel();

        labelNome.setText("Nome:");
        inputNome = new JTextField(40);

        labelTexto.setText("Mensagem:");
        inputTexto = new JTextField(40); //os parametros definem o seu tamanho

        JButton buttonEnviar = new JButton("Enviar");
        buttonEnviar.setPreferredSize(new Dimension(180, 30));

        messageArea = new JTextArea(20, 28);
        messageArea.setEditable(false);
        messageArea.setFocusable(false);
        messageArea.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane(messageArea);

        //criando e adicionando os componentes no painel principal
        JPanel mainPanel = new JPanel();
        JPanel painel = new JPanel();
        painel.setSize(450, 500);

        painel.add(labelNome);
        painel.add(inputNome);
        painel.add(labelTexto);
        painel.add(inputTexto);
        painel.add(buttonEnviar);
        painel.add(Box.createVerticalStrut(2));
        painel.add(scrollPane);
        mainPanel.add(painel);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);

        //registrando leitor de ações para enviar dados
        buttonEnviar.addActionListener(this);
        inputTexto.addActionListener(this);

        frame.add(painel);
        frame.add(mainPanel);
        frame.setVisible(true);
        frame.setTitle("Mensagens com SQL, Swing e Java");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // exibir e atualizar mensagens na tela
    public void updateMessages() {
        StringBuilder msgs = new StringBuilder();
        try{
            Connection con = DriverManager.getConnection(url, user, password);
            String sql = "SELECT remetente, texto FROM mensagens";
            Statement stmt = con.createStatement();
            ResultSet values = stmt.executeQuery(sql);

            while(values.next()){
                String nome = values.getString(1);
                String mensagem = values.getString(2);
                msgs.append(String.format("[%s]: %s\n\n", nome, mensagem));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        messageArea.setText(msgs.toString());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = inputTexto.getText();
        String rem = inputNome.getText();

        // evitar que o usuário mande dados em branco
        if (!(msg.isEmpty() || rem.isEmpty())) {
            try {
                Connection con = DriverManager.getConnection(url, user, password);
                String sql = "INSERT INTO mensagens(remetente, texto) VALUES (?, ?)";
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, rem);
                pstmt.setString(2, msg);
                pstmt.execute();

                ResultSet values = pstmt.executeQuery("select * from mensagens");

                while (values.next()) {
                    System.out.println("\nid: " + values.getInt(1));
                    System.out.println("Nome: " + values.getString(2));
                    System.out.println("Mensagem: " + values.getString(3));
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

        updateMessages();
    }
}