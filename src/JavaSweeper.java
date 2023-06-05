import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.*;
import sweeper.Box;
import sweeper.Coord;
import sweeper.Game;
import sweeper.Ranges;

public class JavaSweeper extends JFrame
{
    private Game game; // Класс контроллер содержит всю логику игры
    private JPanel panel;
    private JLabel label;
    private final int COLS = 9;
    private final int ROWS = 9;
    private final int BOMBS = 10;
    private final int IMAGE_SIZE = 50;

    public static void main(String[] args)
    {
        // создание окна программы
        new JavaSweeper();
    }

    private JavaSweeper()
    {
        // устанавливаем размер
        game = new Game(COLS, ROWS, BOMBS);
        // запускаем игру
        game.start();
        // Грузим картинки
        setImages();
        // Создаём label
        initLabel();
        // Создаём панель
        initPanel();
        // Делаем инициализацию параметров окна
        InitFrame();
    }
    private void initLabel()
    {
        label = new JLabel("Welcome!");
        add(label, BorderLayout.SOUTH);
    }
    private void initPanel()
    {
        // Создаём панел
        panel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                // рисуем картинки
                for (Coord coord : Ranges.getAllCoords())
                {
                    g.drawImage((Image) game.getBox(coord).image,
                            coord.x * IMAGE_SIZE,coord.y * IMAGE_SIZE, this);
                }
            }


        };

        // делаем инициализацию мышки
        panel.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                // обрабатываем мышку
                int x = e.getX() / IMAGE_SIZE;
                int y = e.getY() / IMAGE_SIZE;
                Coord coord = new Coord(x,y);
                if (e.getButton() == MouseEvent.BUTTON1)
                    game.pressLeftButton(coord);
                if (e.getButton() == MouseEvent.BUTTON3)
                    game.pressRightButton(coord);
                if (e.getButton() == MouseEvent.BUTTON2)
                    game.start();
                label.setText(getMessage());
                panel.repaint();
            }
        });

        // Устанавливаем размеры панели
        panel.setPreferredSize(new Dimension(
                Ranges.getSize().x * IMAGE_SIZE,
                Ranges.getSize().y * IMAGE_SIZE));
        // добавляем панель на форму
        add(panel);
    }

    private String getMessage()
    {
        switch (game.getState())
        {
            case PLAYED : return "Think twice!";
            case BOMBED : return "YOU LOSE! BIG BA-DA-BOOM!";
            case WINNER : return "CONGRATULATIONS!";
            default : return "Welcome!";
        }
    }

    private void InitFrame()
    {
        // установим параметры закрытия окна по умолчанию. Чтобы приложение закрылось
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // Установим заголовок
        setTitle("Java Sweeper");
        // Изменим форму , чтобы всё поместилось
        pack();
        // Отключаем изменение размера окна
        setResizable(false);
        // Установим видимость формы
        setVisible(true);
        // Установим, чтобы окно было по центру
        setLocationRelativeTo(null);
        // Установить ICon для программы
        setIconImage(getImage("icon"));
    }

    private void setImages()
    {
        // Перебор картинок в Box
        for (Box box : Box.values())
            box.image=getImage(box.name().toLowerCase());
    }

// Функция получения картинки
    private Image getImage(String name)
    {
        String filename = "img/"+name+".png";

        // getClass().getResource(filename) можно использовать если сделать папку res Resurce Rot(в меню на папке)
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
        return icon.getImage();

    }

}
