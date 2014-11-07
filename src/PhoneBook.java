import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class PhoneBook {

    LinkedList<Contact> Book = new LinkedList<Contact>();
    JFrame BookFrame = new JFrame("Адресная книга");
    JPanel jpButton = new JPanel();
    // JTable jtFieldInfo =  new JTable();
    // JScrollPane jspFieldInfo = new JScrollPane(jtFieldInfo);
    JTextArea jtaFieldInfo = new JTextArea();
    JLabel jlFieldInfo = new JLabel("Информация");
    JButton jbSearch = new JButton("Найти");
    JButton jbAdd = new JButton("Добавить");
    JButton jbEdit = new JButton("Изменить");
    JButton jbExit = new JButton("Выход");
    JTextField jtSearchField = new JTextField();

    // ---------- Конструктор ----------//
    PhoneBook() {
        /*Book.add(new Contact("Ivan", "111111", "ivan@mail.ru"));
        Book.add(new Contact("Petr", "222222", "petr@mail.ru"));
        Book.add(new Contact("Polina", "333333", "polina@mail.ru"));
        Book.add(new Contact("Fedor", "444444", "fedor@mail.ru"));*/

        // ---------- Параметры ---------
        // ---------- Параметры окна
        BookFrame.getContentPane().setLayout(new GridBagLayout());
        BookFrame.setBounds(550, 250, 400, 300);
        BookFrame.setResizable(true);
        BookFrame.setVisible(true);
        BookFrame.getContentPane().setBackground(Color.lightGray);
        BookFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ---------- Добавление поля и кнопок
        BookFrame.getContentPane().add(jtSearchField, new GridBagConstraints(0, 0, 1, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(5, 5, 0, 0), 0, 0));
        BookFrame.getContentPane().add(jbSearch, new GridBagConstraints(0, 1, 1, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(5, 5, 0, 0), 0, 0));
        BookFrame.getContentPane().add(jbAdd, new GridBagConstraints(0, 2, 1, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(5, 5, 0, 0), 0, 0));
        BookFrame.getContentPane().add(jbEdit, new GridBagConstraints(0, 3, 1, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(5, 5, 0, 0), 0, 0));
        BookFrame.getContentPane().add(jbExit, new GridBagConstraints(0, 4, 1, 1, 0, 0,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(5, 5, 0, 0), 0, 0));
        BookFrame.getContentPane().add(jpButton, new GridBagConstraints(0, 5, 1, 2, 0, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(5, 5, 5, 0), 0, 0));
        BookFrame.getContentPane().add(jtaFieldInfo, new GridBagConstraints(1, 1, 1, 6, 1, 0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
        BookFrame.getContentPane().add(jlFieldInfo, new GridBagConstraints(1, 0, 1, 1, 1, 0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));

        // ---------- Параметры поля
        Font fontField = new Font("Verdana", Font.PLAIN, 14);
        jtaFieldInfo.setBorder(BorderFactory.createLineBorder(Color.black));
        jtaFieldInfo.setFont(fontField);
        jtaFieldInfo.setEditable(false);
        jpButton.setBackground(Color.lightGray);

        // ---------- Параметры поля 2
        jlFieldInfo.setFont(fontField);
        jlFieldInfo.setHorizontalAlignment(SwingConstants.CENTER);

        // ---------- Вешаем обработчики ---------- //
        // ---------- Выход
        jbExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        // ---------- Найти
        jbSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionSearch();
            }
        });
        // ---------- Найти Enter
        jtSearchField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionSearch();
            }
        });
        // ---------- Добавить
        jbAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ContactFrame();
            }
        });
        // ---------- Изменить
        jbEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             /*   String q="";
                for (int i =0; i<Book.size(); i++){
                    q+= Book.get(i)+ "\n";
                }
fieldInfo.setText(q);*/
                FileReader myFile = null;
                BufferedReader buff = null;
                String found = "";
                try {
                    myFile = new FileReader("PhoneBook.dat");
                    buff = new BufferedReader(myFile);
                    while (true) {
                        String line = buff.readLine();
                        if (line == null) break;
                        found += line + "\n";
                    }
                    jtaFieldInfo.setText(found);
                } catch (IOException le) {
                    le.printStackTrace();
                } finally {
                    try {
                        buff.close();
                        myFile.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }  // ---------- Конец Конструкторф -------- //

    // ---------- Search
    public void actionSearch() {
        String found = "";
        for (Contact newEntry : Book) {
            if (newEntry.name.contains(jtSearchField.getText())) {
                found += newEntry.phone + "\n";
            }
        }
        if (found.isEmpty()) {
            jtaFieldInfo.setText("Not Found");
        } else {
            jtaFieldInfo.setText(found);
        }
    }// ---------- End Search

    // <<---------- Main ---------->> //
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PhoneBook();
            }
        });

    } // <<--------- End Main --------->> //

    public class Contact {
        String name;
        String phone;
        String email;

        Contact(String n, String p, String e) {
            name = n;
            phone = p;
            email = e;
        }
    }

    public class ContactFrame {

        JFrame jfContactFrame = new JFrame("Новый контакт");
        JButton jbAdd = new JButton("Ок");
        JButton jbCancel = new JButton("Отмена");
        JTextField tfName = new JTextField();
        JTextField tfEmail = new JTextField();
        JTextField tfPhone = new JTextField();
        JLabel lName = new JLabel("Имя: ");
        JLabel lPhone = new JLabel("Телефон: ");
        JLabel lEmail = new JLabel("E-mail: ");
        JLabel lImage = new JLabel(new ImageIcon("no_ava.jpg"));

        ContactFrame() {
            // ---------- Параметры окна
            jfContactFrame.setBounds(550, 250, 400, 200);
            jfContactFrame.setResizable(false);
            jfContactFrame.setVisible(true);
            jfContactFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

            // ---------- Поле ввода
            jfContactFrame.getContentPane().setLayout(new GridBagLayout());
            jfContactFrame.getContentPane().add(lImage, new GridBagConstraints(0, 0, 1, 3, 0, 0, GridBagConstraints.NORTH,
                    GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
            jfContactFrame.getContentPane().add(lName, new GridBagConstraints(1, 0, 1, 1, 0, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(5, 5, 0, 5), 0, 0));
            jfContactFrame.getContentPane().add(tfName, new GridBagConstraints(2, 0, 2, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(5, 5, 0, 5), 0, 0));
            jfContactFrame.getContentPane().add(lPhone, new GridBagConstraints(1, 1, 1, 1, 0, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));
            jfContactFrame.getContentPane().add(tfPhone, new GridBagConstraints(2, 1, 2, 1, 1, 0,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));
            jfContactFrame.getContentPane().add(lEmail, new GridBagConstraints(1, 2, 1, 1, 0, 1,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));
            jfContactFrame.getContentPane().add(tfEmail, new GridBagConstraints(2, 2, 2, 1, 1, 1,
                    GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

            // ---------- Кнопки
            jfContactFrame.getContentPane().add(jbAdd, new GridBagConstraints(2, 3, 1, 1, 1, 0, GridBagConstraints.LINE_END,
                    GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
            jfContactFrame.getContentPane().add(jbCancel, new GridBagConstraints(3, 3, 1, 1, 0, 0, GridBagConstraints.CENTER,
                    GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));

            // ---------- Вешаем обработчики ----------
            // ---------- Отмена
            jbCancel.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jfContactFrame.setVisible(false);
                }
            });

            // ---------- Добавить
            jbAdd.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Contact NewContact = new Contact(tfName.getText(), tfPhone.getText(), tfEmail.getText());
                    Book.add(NewContact);
                    jfContactFrame.dispose();
                }
            });
        }   //---------- End AddToBook --------
    }// ---------- End Contact
}
