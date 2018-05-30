package practicacifradosimetrico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PracticaCifradoSimetrico {
                static int[] miCifrado;
                static int n = 7;
                static String miLlave;
	public static void main(String[] args) {

		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame(" Algoritmo de Cifrado Simetrico ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JTextArea Encriptado = new JTextArea();
    JTextArea desEncriptado = new JTextArea();

    JButton encriptarB = new JButton(" Iniciar Cifrado ");
    JButton desencriptarB = new JButton(" Iniciar Descifrado ");
    JButton BorrarB = new JButton(" Borrar resultados ");

    JTextField claveLlave1 = new JTextField(20);
    JTextField aEncriptar = new JTextField(20);
    JTextField aDesencriptar = new JTextField(20);

    JLabel j1 = new JLabel(" Texto a cifrar ");
    JLabel j2 = new JLabel(" Clave / LLave ");
    JLabel j3 = new JLabel(" Texto a descifrar ");


    JScrollPane scrollPanel1 = new JScrollPane(Encriptado, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPanel1.setPreferredSize(new Dimension(300, 200));

    JScrollPane scrollPanel2 = new JScrollPane(desEncriptado, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPanel2.setPreferredSize(new Dimension(300, 200));

    JScrollPane scrollPanel3 = new JScrollPane(aDesencriptar, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPanel2.setPreferredSize(new Dimension(300, 200));

    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JPanel panel4 = new JPanel();

    panel1.setBorder(BorderFactory.createTitledBorder(""));
    panel2.setBorder(BorderFactory.createTitledBorder("Texto Cifrado"));
    panel3.setBorder(BorderFactory.createTitledBorder(""));
    panel4.setBorder(BorderFactory.createTitledBorder("Texto Descrifrado"));


		BoxLayout layout1 = new BoxLayout(panel1, BoxLayout.Y_AXIS);
		BoxLayout layout2 = new BoxLayout(panel2, BoxLayout.Y_AXIS);
                BoxLayout layout3 = new BoxLayout(panel3, BoxLayout.Y_AXIS);
                BoxLayout layout4 = new BoxLayout(panel4, BoxLayout.Y_AXIS);

                panel1.setLayout(layout1);
		panel2.setLayout(layout2);
                panel3.setLayout(layout3);
                panel4.setLayout(layout4);

		panel1.add(j1);
                panel1.add(aEncriptar );
		panel1.add(j2);
		panel1.add(claveLlave1);
                panel1.add(encriptarB);

                panel2.add(scrollPanel1);

                panel3.add(j3);
                panel3.add(scrollPanel3);
                panel3.add(desencriptarB);

                panel4.add(scrollPanel2);

		frame.setLayout(new FlowLayout());
		frame.add(panel1);
                frame.add(panel2);
                frame.add(panel3);
                frame.add(panel4);
                frame.add( BorrarB);

		frame.pack();
		frame.setVisible(true);
                frame.setSize(1400, 300);
                //frame.setBackground(Color.blue);
                frame.setLocationRelativeTo(null);

               BorrarB.addActionListener((ActionEvent e) -> {
                   aEncriptar.setText(null);
                   claveLlave1.setText(null);
                   Encriptado.setText(null);
                   desEncriptado.setText(null);
                   aDesencriptar.setText(null);

                   //aEncriptar.setText("");
                   //claveLlave1.setText("");
                   //Encriptado.setText("");
                   //desEncriptado.setText("");
                   //aDesencriptar.setText("");
                });

                encriptarB.addActionListener((java.awt.event.ActionEvent evt) -> {
                    try{
                        String miSust;
                        String oux;
                        String tran;
                        String sustFinal="";
                        String transtFinal="";
                        String finalCif = "";
                        int elMod;

                        miSust = aEncriptar.getText();
                        miLlave = claveLlave1.getText();

                        for(int i=0 ; i<miSust.length() ; i++) {
                            char miVar = miSust.charAt(i);
                            sustFinal+= ((char)(miVar-6));
                        }
                        System.out.println("\nSustituido :");
                        System.out.println(sustFinal);

                        tran = sustFinal;

                        if((elMod = tran.length()%n) != 0) {
                            elMod = n-elMod;
                            for(int a = elMod; a != 0; a--) { //vacio
                                tran += " "; //el bueno
                            }
                        }
                        System.out.println("\n Matriz:");
                        for(int i=0 ; i<n ; i++) {
                            for(int j=0 ; j<tran.length()/n ; j++) {
                                String miVar = Character.toString((tran.charAt(i+(j*n))));
                                System.out.print(miVar);
                                transtFinal+=(miVar);
                            }
                            System.out.println();
                        }
                        System.out.println("\nFinal cifrado:");
                        System.out.println(transtFinal);

                        System.out.println("\nCesar cifrado");
                        oux = cifrar(transtFinal, miLlave);
                        System.out.println(oux);

                        //XOR cifrado
                        System.out.println("\nXOR cifrado");
                        miCifrado = encrXOR(oux,miLlave);
                        for(int i = 0; i < miCifrado.length; i++){
                            System.out.printf("%d,", miCifrado[i]);
                            finalCif+=(miCifrado[i]+",");
                        }
                        System.out.println("");

                        Encriptado.setText(finalCif);
                        aDesencriptar.setText(finalCif);

                    }catch(Exception e){
                        JOptionPane.showMessageDialog(null,"Texto a cifrar incorrecto", " Error! ", JOptionPane.ERROR_MESSAGE);
                    }
                });

                        desencriptarB.addActionListener((java.awt.event.ActionEvent evt) -> {
                            try{
                                String conTrans ="";
                                String normal="";
                                //XOR descifrado
                                System.out.println("\nXOR descifrado");
                                String xorDescifrado = desencrXOR(miCifrado,miLlave);
                                System.out.println(xorDescifrado);
                                //Cesar descifrado
                                System.out.println("\nCesar descifrado");
                                String cesarDescifrado = descifrar(xorDescifrado, miLlave);
                                System.out.println(cesarDescifrado);

                                n = cesarDescifrado.length()/n;

                                for(int i=0 ; i<n ; i++) {
                                    for(int j=0 ; j<cesarDescifrado.length()/n ; j++) {
                                        char miVar = cesarDescifrado.charAt(i+(j*n));
                                        conTrans+=(miVar);
                                    }
                                }

                                for(int i=0 ; i<conTrans.length() ; i++) {
                                    char miVar = conTrans.charAt(i);
                                    normal+=((char) (miVar+6));
                                }

                                System.out.println("\nTexto = resultado descifrado: ");
                                System.out.println(normal);

                                desEncriptado.setText(normal);

                            }catch(Exception e){
                                JOptionPane.showMessageDialog(null, "Texto a cifrar incorrecto", " Error! ", JOptionPane.ERROR_MESSAGE);
                            }
                });
                }

         private static String cifrar(String ingreso, final String miLlave) {
                String res = "";
                for (int i = 0; i < ingreso.length(); i++) {
                    char miVar = ingreso.charAt(i);
                    int cesar = miLlave.charAt(i % miLlave.length())-64;
                    int sum = miVar - 64 + cesar;
                    res += (char) (sum % 95+64);
                }
                return res;
            }
         private static int[] encrXOR(String ingreso, String miLlave) {
                int[] salida = new int[ingreso.length()];
                for(int i = 0; i < ingreso.length(); i++) {
                    int sal = (Integer.valueOf(ingreso.charAt(i)) * Integer.valueOf(miLlave.charAt(i % (miLlave.length() - 1)))) + '0';
                    salida[i] = sal;
                }
                return salida;
            }
          private static String descifrar(String ingreso, final String miLlave) {
                String res = "";
                for (int i = 0; i < ingreso.length(); i++) {
                    char miVar = ingreso.charAt(i);
                    int cesar = miLlave.charAt(i % miLlave.length()) - 64;
                    int sum = miVar - 64+(95 - cesar);
                    res += (char) (sum %95+64);
                }
                return res;
            }
          private static String desencrXOR(int[] ent, String miLlave) {
                String Mifinal = "";
                for(int i = 0; i < ent.length; i++) {
                    Mifinal += (char) ((ent[i] - 46) / (int) miLlave.charAt(i % (miLlave.length() - 1)));
                }
                return Mifinal;
            }
 }
