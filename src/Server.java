import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by User on 06.07.2016.
 */
public class Server{

    JFrame frame;
    JButton sendButton;
    JPanel panel;
    JTextField nameTextField, serviceTextField, percentTextField;

    public Server(){
        frame = new JFrame("AutoRate discount");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(410,100);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());
        sendButton =  new JButton("Send discount");
        nameTextField = new JTextField(10);
        serviceTextField = new JTextField(10);
        percentTextField = new JTextField(10);
        panel = new JPanel();
        panel.add(nameTextField);
        panel.add(serviceTextField);
        panel.add(percentTextField);
        panel.add(sendButton);
        frame.getContentPane().add(BorderLayout.CENTER,panel);
        frame.setLocationRelativeTo(null);

        sendButton.addActionListener(new MyListener());
    }

    private static final int portNumber = 60123;

    public static void main(String[] args) {

        Server server  = new Server();
        ServerSocket serverSocket = null;

        try{

            System.out.println("Server started at port number " + portNumber);
            serverSocket = new ServerSocket(portNumber);

            // Waiting for client connection
            System.out.println("Waiting for client connection");
            Socket socket = serverSocket.accept();
            System.out.println("A client has connected");

            // Send message to the client
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String s = server.nameTextField.getText() + server.serviceTextField.getText() + server.percentTextField;
            bufferedWriter.write(s);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            // Receive message from client
            String data;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while((data = bufferedReader.readLine()) != null)
                System.out.println("Message from client " + data);
            System.out.println("Server has ended");


        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private class MyListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
