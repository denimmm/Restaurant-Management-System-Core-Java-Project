import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import java.util.*;

public class UserInterface_GUI extends JFrame implements ActionListener {
    private Container       con;
    private Controller_GUI  rcController;
    private String          currentUserName;

    // components for menu
    private JMenuBar   menuBar;
    private JMenu      mnFile;
    private JMenuItem  mntm1, mntm2;

    //-------- Master panel --------------
    //Main content panel(CENTER)
    private JPanel         mainPanel;

    //Head panel (North)
    private JPanel         headPanel;
    private JLabel         headTitle;
    private JButton        headBtnLogin;
    private JButton        headBtnLogout;

    //Main button panel(WEST)
    private JPanel         mainBtnsPanel;
    private JButton        mainBtnShowMenu;
    private JButton        mainBtnManageOrder;
    private JButton        mainBtnManageEmployee;
    private JButton        mainBtnManageMenuItem;
    private JButton        mainBtnShowTotalSales;
    private JButton        mainBtnShowPayment;
    private messageButton        mainBtnMessage;


    /// @brief Custom button for pizza customization (visible only for pizza)
    /// @author Alex Ratchev
    private JButton        customButton;

    //Information panel(SOUTH)
    private JPanel         infoPanel;
    private JLabel         labelLoginUserName;
    private JButton        btnClockOut;
    private JTextArea      taMessage;

    //-------- Contents panel --------------
    private JPanel         homePanel;
    private JLabel         homeImage;

    private LoginPanel          cLoginPanel;
    private MenuListPanel       cMenuListPanel;
    private OrderListPanel      cOrderListPanel;
    private OrderDetailPanel    cOrderDetailPanel;
    private EmployeeListPanel   cEmployeeListPanel;
    private EditEmployeePanel   cEditEmployeePanel;
    private MenuManagementPanel cMenuManagementPanel;
    private EditMenuItemPanel   cEditMenuItemPanel;
    private TotalSalesPanel     cTotalSalesPanel;
    private PaymentPanel        cPaymentPanel;
    
    // ----- Message panel for message screen -------
    private JPanel         messagePanel;
    private JTextArea      messageScreen;
    // add the write and display buttons
    private JButton        btnWriteMessage;
    private JButton        btnDisplayMessages;
    private JButton        btnSend; // Send button for writing messages

    

    private final static int WINDOW_X = 100;
    private final static int WINDOW_Y = 100;
    private final static int WINDOW_WIDTH = 900;
    private final static int WINDOW_HEIGHT = 600;

    public UserInterface_GUI(Controller_GUI rController) {
        this.rcController = rController;
        this.con = getContentPane();

        setTitle("Valentino Restaurant Management System");
        setBounds(WINDOW_X, WINDOW_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createMasterPanelConpornents();
        currentUserName = "";
        setLoginUserName(currentUserName);

        homePanel = new JPanel();
        homeImage = new JLabel();

        int i = new Random().nextInt(4)+1;
        homeImage.setHorizontalAlignment(SwingConstants.CENTER);
        homeImage.setVerticalAlignment(SwingConstants.CENTER);
        homeImage.setIcon(new ImageIcon("images/home" + i + ".jpg"));
        homePanel.add(homeImage);
        homePanel.setBackground(Color.WHITE);
        mainPanel.add("Home", homePanel);

        cLoginPanel = new LoginPanel();
        mainPanel.add("Login", cLoginPanel);
        cMenuListPanel = new MenuListPanel();
        mainPanel.add("MenuList", cMenuListPanel);
        cOrderListPanel = new OrderListPanel();
        mainPanel.add("OrderList", cOrderListPanel);
        cOrderDetailPanel = new OrderDetailPanel();
        mainPanel.add("OrderDetail", cOrderDetailPanel);
        cEmployeeListPanel = new EmployeeListPanel();
        mainPanel.add("EmployeeList", cEmployeeListPanel);
        cEditEmployeePanel = new EditEmployeePanel();
        mainPanel.add("EditEmployee", cEditEmployeePanel);
        cMenuManagementPanel = new MenuManagementPanel();
        mainPanel.add("MenuManagement", cMenuManagementPanel);
        cEditMenuItemPanel = new EditMenuItemPanel();
        mainPanel.add("EditMenuItem", cEditMenuItemPanel);
        cTotalSalesPanel = new TotalSalesPanel();
        mainPanel.add("TotalSalesPanel", cTotalSalesPanel);
        cPaymentPanel = new PaymentPanel();
        mainPanel.add("PaymentPanel", cPaymentPanel);

        // Message panel
        messagePanel = new JPanel(new BorderLayout());
    
        // Text area for displaying messages
        messageScreen = new JTextArea(10, 30);
        messageScreen.setEditable(false);
        messageScreen.setBorder(BorderFactory.createTitledBorder("Messages"));
        messagePanel.add(new JScrollPane(messageScreen), BorderLayout.CENTER);

        // Panel for Write and Display buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnWriteMessage = new JButton("Write");
        btnDisplayMessages = new JButton("Display");
        btnSend = new JButton("Send"); 
        //btnSend.setEnabled(false); // Initially disabled

        // Add action listeners for the buttons
        btnWriteMessage.addActionListener(this);
        btnDisplayMessages.addActionListener(this);

        // Add buttons to the button panel
        buttonPanel.add(btnWriteMessage);
        buttonPanel.add(btnDisplayMessages);
        buttonPanel.add(btnSend); // Add the Send button to the panel

        // Add the button panel to the message panel
        messagePanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the message panel to the mainPanel
        mainPanel.add("Message", messagePanel);


        /// @brief This is a placeholder to set the button to visable
        /// @author Alex Ratchev
        customButton = new JButton("Custom");
        customButton.setVisible(true); // it is not directly used in this frame

        changeMode(MODE_ANONYMOUS);
    }
    
    private void createMasterPanelConpornents()
    {
                // Add menu to frame
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        mnFile = new JMenu("File");
        menuBar.add(mnFile);

        mntm1 = new JMenuItem("[1] Login");
        mnFile.add(mntm1);
        mntm1.addActionListener(this);
        
        mntm2 = new JMenuItem("[2] Exit");
        mnFile.add(mntm2);
        mntm2.addActionListener(this);
        
        //----------- Create main panels ------------
        con.setLayout(new BorderLayout());
        
        //head panel
        headPanel = new JPanel();
        headPanel.setBackground(Color.BLACK);
        headPanel.setLayout(new FlowLayout());
        headTitle = new JLabel("Valentino Restaurant Management System");
        headTitle.setForeground(Color.WHITE);
        headTitle.setPreferredSize(new Dimension(500, 30));
        headTitle.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));
        headBtnLogin = new JButton("Login");
        headBtnLogin.addActionListener(this);
        headBtnLogout = new JButton("Logout");
        headBtnLogout.addActionListener(this);
        headPanel.add(headTitle);
        headPanel.add(headBtnLogin);
        headPanel.add(headBtnLogout);
        con.add(headPanel, BorderLayout.NORTH);
        
        //main content panel
        mainPanel = new JPanel();
        mainPanel.setOpaque(true);
        mainPanel.setLayout(new CardLayout());
        con.add(mainPanel, BorderLayout.CENTER);
        
        //main operate buttons panel
        mainBtnsPanel = new JPanel();
        mainBtnsPanel.setLayout(new GridLayout(0,1));
        
        mainBtnShowMenu = new JButton("Show menu");
        mainBtnShowMenu.addActionListener(this);
        mainBtnsPanel.add(mainBtnShowMenu);
        
        mainBtnManageOrder = new JButton("Order management");
        mainBtnManageOrder.addActionListener(this);
        mainBtnsPanel.add(mainBtnManageOrder);
        
        mainBtnManageEmployee = new JButton("Manage employees");
        mainBtnManageEmployee.addActionListener(this);
        mainBtnsPanel.add(mainBtnManageEmployee);
        
        mainBtnManageMenuItem = new JButton("Manage menu items");
        mainBtnManageMenuItem.addActionListener(this);
        mainBtnsPanel.add(mainBtnManageMenuItem);
        
        mainBtnShowTotalSales = new JButton("Show total sales");
        mainBtnShowTotalSales.addActionListener(this);
        mainBtnsPanel.add(mainBtnShowTotalSales);
        
        mainBtnShowPayment = new JButton("Show payments");
        mainBtnShowPayment.addActionListener(this);
        mainBtnsPanel.add(mainBtnShowPayment);
        
        //add message button --> Project Design
        
        //check for new messages
        mainBtnMessage = new messageButton();
        
        
        
        mainBtnMessage.addActionListener(this);
        mainBtnsPanel.add(mainBtnMessage);
        
        con.add(mainBtnsPanel, BorderLayout.WEST);
        
        //Information panel
        infoPanel = new JPanel();
        infoPanel.setLayout(new FlowLayout());
        labelLoginUserName = new JLabel();
        labelLoginUserName.setPreferredSize(new Dimension(150, 50));
        taMessage = new JTextArea(3,50);
        taMessage.setEditable(false);
        taMessage.setText("Wellcome!!");
        taMessage.setOpaque(true);
        btnClockOut = new JButton("Clock out");
        btnClockOut.setEnabled(false);
        btnClockOut.addActionListener(this);
        LineBorder border = new LineBorder(Color.BLACK, 3, true);
        taMessage.setBorder(border);
        taMessage.setBackground(Color.WHITE);
        infoPanel.add(labelLoginUserName);
        infoPanel.add(btnClockOut);
        infoPanel.add(taMessage);
        con.add(infoPanel, BorderLayout.SOUTH);
    }
    
    private class messageButton extends JButton{

		public messageButton() {
    		super("Messages");
    	}
    	
        public void updateText() {
        	
        	//get the current user
        	Staff currentUser = rcController.getStaffData(rcController.getCurrentUserID());
        	
        	
        	
        	//determine if the user has a new message
        	if (currentUser != null && currentUser.hasNewAnnouncement()) {
            	//makes the button
        	    this.setText("** Messages **");
        		
        	}
        	else {
    	    	//makes the button
        	    this.setText("Messages");
    	    	
        	}
        }
    }
    

    

    public void setLoginUserName(String newName)
    {
        currentUserName = newName;
         if(newName == "")
         {
             labelLoginUserName.setText("Please login first.");
         }
         else
         {
            labelLoginUserName.setText("<html>Login user<br>" + newName + "</html>");
        }
    }

    public final static byte MODE_ANONYMOUS = 0;
    public final static byte MODE_EMPLOYEE = 1;
    public final static byte MODE_MANAGER = 2;
    
    public void changeMode(byte state)
    {
        switch(state)
        {
            case MODE_ANONYMOUS:
                headBtnLogout.setEnabled(false);
                mainBtnShowMenu.setEnabled(false);
                mainBtnManageOrder.setEnabled(false);
                mainBtnManageEmployee.setEnabled(false);
                mainBtnManageMenuItem.setEnabled(false);
                mainBtnShowTotalSales.setEnabled(false);
                mainBtnShowPayment.setEnabled(false);
                mainBtnMessage.setEnabled(false);
                btnDisplayMessages.setEnabled(false);
                btnWriteMessage.setEnabled(false);
                btnSend.setEnabled(false);
                break;
            case MODE_EMPLOYEE:
                headBtnLogout.setEnabled(true);
                mainBtnShowMenu.setEnabled(true);
                mainBtnManageOrder.setEnabled(true);
                mainBtnManageEmployee.setEnabled(false);
                mainBtnManageMenuItem.setEnabled(false);
                mainBtnShowTotalSales.setEnabled(false);
                mainBtnShowPayment.setEnabled(false);
                mainBtnMessage.setEnabled(true);
                btnDisplayMessages.setEnabled(true);
                btnWriteMessage.setEnabled(false);
                btnSend.setEnabled(false);
                break;
           case MODE_MANAGER:
                headBtnLogout.setEnabled(true);
                mainBtnShowMenu.setEnabled(true);
                mainBtnManageOrder.setEnabled(true);
                mainBtnManageEmployee.setEnabled(true);
                mainBtnManageMenuItem.setEnabled(true);
                mainBtnShowTotalSales.setEnabled(true);
                mainBtnShowPayment.setEnabled(true);
                mainBtnMessage.setEnabled(true);
                btnDisplayMessages.setEnabled(true);
                btnWriteMessage.setEnabled(true);
                btnSend.setEnabled(true);
                break;
        }
    }
    
    public void setTodaysDate(String today)
    {
        ////
    }
    
    void setClockOutButton()
    {
        if(rcController.checkIfUserClockedOut())
            btnClockOut.setEnabled(true);
        else
            btnClockOut.setEnabled(false);
    }
    //--------------------------------------------------------
    // Display message on an information panel
    //--------------------------------------------------------
    public void displayMessage(String message)
    {
        taMessage.setForeground(Color.BLACK);
        taMessage.setText(message);
    }
    
    public void displayErrorMessage(String message)
    {
        taMessage.setForeground(Color.RED);
        taMessage.setText(message);
    }
    
    //========================================================
    // Show dialog message
    //========================================================
    final static int DIALOG_YES = JOptionPane.YES_OPTION;
    final static int DIALOG_NO = JOptionPane.NO_OPTION;
    final static int DIALOG_CANCEL = JOptionPane.CANCEL_OPTION;
    
    public int showYesNoDialog(String title, String message)
    {
        int option = JOptionPane.showConfirmDialog(this, message, title, JOptionPane.YES_NO_OPTION, 
        JOptionPane.QUESTION_MESSAGE);
        return option;
    }
    
    public int showYesNoCancelDiaglog(String title, String message)
    {
        int option = JOptionPane.showConfirmDialog(this, message, title, JOptionPane.YES_NO_CANCEL_OPTION, 
        JOptionPane.QUESTION_MESSAGE);
        return option;
    }
    
    public void showErrorDialog(String title, String message)
    {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }
    
    public void showConfirmDialog(String title, String message)
    {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.PLAIN_MESSAGE);
    }
        

    
    
    private int getIDfromString(String stringLine, int length)
    {
        int index = stringLine.indexOf("ID:"); //Search string of "ID:"
        if(index == -1)
        {
            showErrorDialog("Error", "String 'ID:' is not found!!");
            return -1;
        }
        
        try
        {
            String strID = stringLine.substring(index + 3, index + 3 + length);
            int id = Integer.parseInt(strID.trim());
            return id;
        }
        catch(Exception e)
        {
            showErrorDialog("Error", "Parse error");
            return -1;
        }
    }
    //========================================================
    // Master panel action
    //========================================================
    public void actionPerformed(ActionEvent ae) {

        if(ae.getSource() == mntm2)
        {
            System.exit(0);
        }
        else if (ae.getSource() == mainBtnShowMenu)
        {
            //((CardLayout) mainPanel.getLayout()).show( mainPanel, "MenuList");
            changeMainPanel("MenuList");
            cMenuListPanel.init();
        }
        else if (ae.getSource() == mainBtnManageOrder)
        {
            //((CardLayout) mainPanel.getLayout()).show( mainPanel, "OrderList");
            changeMainPanel("OrderList");
            cOrderListPanel.init();
        }
        else if (ae.getSource() == mainBtnManageEmployee)
        {
            changeMainPanel("EmployeeList");
            cEmployeeListPanel.init();
        }
        else if (ae.getSource() == mainBtnManageMenuItem)
        {
            changeMainPanel("MenuManagement");
            cMenuManagementPanel.init();
        }
        else if (ae.getSource() == mainBtnShowTotalSales)
        {
            changeMainPanel("TotalSalesPanel");
            cTotalSalesPanel.init();
        }
        else if (ae.getSource() == mainBtnShowPayment)
        {
            changeMainPanel("PaymentPanel");
            cPaymentPanel.init();
        }
        else if (ae.getSource() == headBtnLogin || ae.getSource() == mntm1) {
            changeMainPanel("Login");
            cLoginPanel.init();
            displayMessage("Enter your login ID and password.");
        }
        else if (ae.getSource() == headBtnLogout) {
            if( showYesNoDialog("Logout","Are you sure to logout?") == DIALOG_YES)
            {
                rcController.userLogout();
                changeMainPanel("Home");
                changeMode(MODE_ANONYMOUS);
                setClockOutButton();
            }
        }
        else if (ae.getSource() == btnClockOut){
            if( showYesNoDialog("Clock out","Are you sure to clock out?") == DIALOG_YES)
            {
                rcController.clockOut();
                setClockOutButton();
            }
        }
        else if (ae.getSource() == mainBtnMessage){
            changeMainPanel("Message");
            messageScreen.setText("");
            if(rcController.getUserType() ==2){
                btnWriteMessage.setEnabled(true);
                btnDisplayMessages.setEnabled(true);
            }
            else{
                btnWriteMessage.setEnabled(false);
                btnDisplayMessages.setEnabled(true);
            }
        }

        else if (ae.getSource() == btnWriteMessage) {
    
            messageScreen.setEditable(true);
            messageScreen.setText("Write your message here...");
    
                // Add the Send button to the button panel
                JPanel buttonPanel = (JPanel) messagePanel.getComponent(1); // Assuming buttonPanel is the second component
                buttonPanel.add(btnSend);
                buttonPanel.revalidate();
                buttonPanel.repaint();
                if (btnSend.getActionListeners().length == 0) {
                    btnSend.addActionListener(e -> {
                        // Check if the text area is editable before sending the message
                        if (!messageScreen.isEditable()) {
                            JOptionPane.showMessageDialog(this, "You cannot send a message while in read-only mode.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        // Get the message from the text area
                        String message = messageScreen.getText().trim();
                        if (message.isEmpty() || message.equals("Write your message here...")) {
                            JOptionPane.showMessageDialog(this, "Message cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
        
                        // Logic to handle the message (e.g., save to file or notify staff)
                        
                        try{
                            rcController.getDatabase().getManagerNotifier().notifyEmployees();
                            rcController.getDatabase().saveAnnouncementState();
                            rcController.getDatabase().saveMessageToDatabase(message);
                            
                        }catch(Exception ex){
                            ex.printStackTrace();
                        }
                        JOptionPane.showMessageDialog(this, "Message sent successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        
                        // Clear the message screen after sending
                        messageScreen.setText("");
                    });
                }
        }
        else if (ae.getSource() == btnDisplayMessages) {
            messageScreen.setEditable(false);
            messageScreen.setText(""); // Clear the screen
            
            //reset the current user's hasAnnouncement state
            Staff currentUser = rcController.getDatabase().findStaffByID(rcController.getCurrentUserID());

            try {
                // Load the entire message from the database
                String message = rcController.getDatabase().loadMessageFromDatabase();
                if (!message.isEmpty()) {
                    //disiplay if current user has new announcement
                    rcController.getDatabase().loadAnnouncementState();
                    if (currentUser.hasNewAnnouncement()) {
                        messageScreen.setText( message);
                        currentUser.setNewAnnouncement(false);//true -->try changing this to true
                        //update file
                        rcController.getDatabase().saveAnnouncementState();
                        //remove notification
                        mainBtnMessage.updateText();
                    }
                }else {
                    messageScreen.setText("No messages to display.");
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error loading messages!", "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }
    
    /****************************************************************
     * Login panel
    *****************************************************************/
    private class LoginPanel extends JPanel implements ActionListener
    {
        // components for login panel
        //private JPanel         loginPanel;
        private JLabel          lblUserID;
        private JTextField      tfUserID;
        private JLabel          lblPassword;
        private JPasswordField  pwPassword;
        private JCheckBox       chbIsManager;
        private JButton         btnLoginOK;
        public LoginPanel()
        {
            //loginPanel = new JPanel();
            GridBagLayout gbLayout = new GridBagLayout();
            this.setLayout( gbLayout);
            GridBagConstraints gbc = new GridBagConstraints();
            lblUserID = new JLabel("UserID:");
            lblUserID.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbLayout.setConstraints(lblUserID, gbc);
            this.add(lblUserID);
            
            tfUserID = new JTextField(20);
            tfUserID.setInputVerifier(new IntegerInputVerifier(0));
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbLayout.setConstraints(tfUserID, gbc);
            this.add(tfUserID);
            
            lblPassword = new JLabel("Password:");
            lblPassword.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbLayout.setConstraints(lblPassword, gbc);
            this.add(lblPassword);
            
            pwPassword = new JPasswordField(20);
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbLayout.setConstraints(pwPassword, gbc);
            this.add(pwPassword);
            
            chbIsManager = new JCheckBox("Login as manager");
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 2;
            gbLayout.setConstraints(chbIsManager, gbc);
            this.add(chbIsManager);
            
            btnLoginOK = new JButton("Login");
            btnLoginOK.addActionListener(this);
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.gridwidth = 2;
            gbLayout.setConstraints(btnLoginOK, gbc);
            this.add(btnLoginOK);
        }
        
        private void setUserID(String id)
        {
            tfUserID.setText(id);
        }
        
        private void setPassword(String password)
        {
            pwPassword.setText(password);
        }
        
        public void init()
        {
            setUserID("");
            setPassword("");
            tfUserID.setBackground( UIManager.getColor( "TextField.background" ) ); 
        }
         
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == btnLoginOK)
            {
                //Check whether current focuced compornent have to verify their value
                if (btnLoginOK.getVerifyInputWhenFocusTarget()) {
                    //Try to get focus
                    btnLoginOK.requestFocusInWindow();
                    if (!btnLoginOK.hasFocus()) {    //Can not get focus ?� the compornent have not been verified
                        return;
                    }
                }  
                //if(!inputVerified)
                //    return;
                    
                char[] password;
                boolean isManager = chbIsManager.isSelected(); 
                
                byte state = -1;
                
                String inputID = tfUserID.getText();
               
                if(inputID.equals(""))
                {
                    displayErrorMessage("Enter user ID");
                    return;
                }
                
     
                password= pwPassword.getPassword();
                String inputPassword = new String(password);
                if(inputPassword.equals(""))
                {
                    displayErrorMessage("Enter password");
                    return;
                }
                
                if( rcController.loginCheck(Integer.parseInt(inputID), inputPassword, isManager))
                {
                    showConfirmDialog("Message", "Login success!!");
                    displayMessage("Wellcome, " + currentUserName);
                    tfUserID.setText("");
                    pwPassword.setText("");
                    changeMainPanel("Home");
                    setClockOutButton();
                    mainBtnMessage.updateText();
                }
                else
                {
                    displayErrorMessage(rcController.getErrorMessage());
                }
            }
        }
    }
    
    private void  changeMainPanel(String panelName)
    {
        ((CardLayout) mainPanel.getLayout()).show( mainPanel, panelName);
        displayMessage("Main panel change :" + panelName);
    }
    
    /****************************************************************
     * Menu list panel
    *****************************************************************/       
    private class MenuListPanel extends JPanel implements ActionListener
    {
        private JScrollPane     scrollPanel;
        private JTextArea       displayArea;
        private JPanel          btnPanel;
        private JButton         btnAll;
        private JButton         btnMain;
        private JButton         btnDrink;
        private JButton         btnAlcohol;
        private JButton         btnDessert;
        
        public MenuListPanel()
        {
            this.setLayout( new BorderLayout());
            displayArea = new JTextArea();
            displayArea.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));
            displayArea.setEditable(false);
            displayArea.setMargin(new Insets(5, 5, 5, 5));
            scrollPanel = new JScrollPane(displayArea);
            scrollPanel.setPreferredSize(new Dimension(200, 400));
            add(scrollPanel, BorderLayout.CENTER);
            
           btnPanel = new JPanel();
           btnPanel.setLayout( new FlowLayout());
           btnAll = new JButton("All");
           btnAll.addActionListener(this);
           btnMain = new JButton("Main");
           btnMain.addActionListener(this);
           btnDrink = new JButton("Drink");
           btnDrink.addActionListener(this);
           btnAlcohol = new JButton("Alcohol");
           btnAlcohol.addActionListener(this);
           btnDessert = new JButton("Dessert");
           btnDessert.addActionListener(this);
           
           btnPanel.add(btnAll);
           btnPanel.add(btnMain);
           btnPanel.add(btnDrink);
           btnPanel.add(btnAlcohol);
           btnPanel.add(btnDessert);
           
           add(btnPanel, BorderLayout.SOUTH);
        }
    
        public void init()
        {
            showMenuList(0);
            //displayArea.setText(str);
            //showAllMenuList(displayArea);
        }
        
        private void showMenuList(int menuType)
        {
            displayArea.setText("");
            ArrayList<String> menuList = rcController.createMenuList(menuType);
            for(int i = 0; i < menuList.size(); i++)
                displayArea.append(menuList.get(i) + "\n");
        }
        
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == btnAll)
            {
                showMenuList(0);
                //showAllMenuList(displayArea);
            }
            else if (ae.getSource() == btnMain)
            {
                showMenuList(MenuItem.MAIN);
                //showParticularMenuList(MenuItem.MAIN, displayArea);
            }
            else if (ae.getSource() == btnDrink)
            {
                showMenuList(MenuItem.DRINK);
                //showParticularMenuList(MenuItem.DRINK, displayArea);
            }
            else if (ae.getSource() == btnAlcohol)
            {
                showMenuList(MenuItem.ALCOHOL);
                //showParticularMenuList(MenuItem.ALCOHOL, displayArea);
            }
            else if (ae.getSource() == btnDessert)
            {
                showMenuList(MenuItem.DESSERT);
                //showParticularMenuList(MenuItem.DESSERT, displayArea);
            }
        }
    }
    
    /****************************************************************
     * MenuManagementPanel
    *****************************************************************/    
    private class MenuManagementPanel extends JPanel implements ActionListener
    {
        private JScrollPane     scrollPanel;
        private JList           displayList;
        private JButton         btnAddNewMenuItem;
        private JButton         btnEditMenuItem;
        private JButton         btnDeleteMenuItem;
        
        public MenuManagementPanel()
        {
            GridBagLayout gbLayout = new GridBagLayout();
            this.setLayout( gbLayout);
            GridBagConstraints gbc = new GridBagConstraints();

            scrollPanel = new JScrollPane();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.gridwidth = 3;
            gbLayout.setConstraints(scrollPanel, gbc);
            this.add(scrollPanel);
            
            btnAddNewMenuItem     = new JButton("Add new menu item");
            btnAddNewMenuItem.addActionListener(this);
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.weighty = 0;
            gbc.weightx = 0.5;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbLayout.setConstraints(btnAddNewMenuItem, gbc);
            this.add(btnAddNewMenuItem);
            
            btnEditMenuItem    = new JButton("Edit menu item");
            btnEditMenuItem.addActionListener(this);
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbLayout.setConstraints(btnEditMenuItem, gbc);
            this.add(btnEditMenuItem);
            
            btnDeleteMenuItem   = new JButton("Delete menu item");
            btnDeleteMenuItem.addActionListener(this);
            gbc.gridx = 2;
            gbc.gridy = 1;
            gbLayout.setConstraints(btnDeleteMenuItem, gbc);
            this.add(btnDeleteMenuItem);
            
            displayList = new JList();
            displayList.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));
            displayList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        }
        
        public void init()
        {
            showMenuList();
        }
        
        private void showMenuList()
        {
            displayList.setListData(rcController.createMenuList(0).toArray());
            scrollPanel.getViewport().setView(displayList);
        }
        
        private int getSelectedMenuID()
        {
            String orderLine = (String)displayList.getSelectedValue();
            if (orderLine == null)
                return -1;
                
            return getIDfromString( orderLine, 4);
        }
        
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == btnAddNewMenuItem)
            {
                cEditMenuItemPanel.init(0);
                changeMainPanel("EditMenuItem");
            }
            else if (ae.getSource() == btnEditMenuItem)
            {
                int menuID = getSelectedMenuID();
                if( menuID == -1)    return;
                cEditMenuItemPanel.init(menuID);
                changeMainPanel("EditMenuItem");
            }
            else if (ae.getSource() == btnDeleteMenuItem)
            {
                int deleteMenuID = getSelectedMenuID();
                if( deleteMenuID == -1)    return;
                
                if( showYesNoDialog("", "Are you sure to delete the menu item?") == DIALOG_YES)
                {
                    if(!rcController.deleteMenuItem(deleteMenuID))
                    {
                        showErrorDialog("Error", rcController.getErrorMessage());
                    }
                    else
                    {
                        displayMessage("Deleted.");
                        init();
                    }
                }
            }
        }
    }
    
    /****************************************************************
     * Edit menu item panel
    *****************************************************************/       
    private class EditMenuItemPanel extends JPanel implements ActionListener
    {
        private JLabel          lblMenuItemID;
        private JTextField      tbMenuItemID;
        private JLabel          lblName;
        private JTextField      tbName;
        private JLabel          lblPrice;
        private JTextField      tbPrice;
        private JLabel          lblType;
        private JComboBox       cbType;
        private JButton         btnOK;
        
        private boolean         isUpdate;
        
        public EditMenuItemPanel()
        {
            GridBagLayout gbLayout = new GridBagLayout();
            this.setLayout( gbLayout);
            GridBagConstraints gbc = new GridBagConstraints();
            
            lblMenuItemID = new JLabel("Menu item ID:");
            lblMenuItemID.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            gbLayout.setConstraints(lblMenuItemID, gbc);
            this.add(lblMenuItemID);
            
            tbMenuItemID = new JTextField(4);
            tbMenuItemID.setInputVerifier(new IntegerInputVerifier(1,10000));
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbLayout.setConstraints(tbMenuItemID, gbc);
            this.add(tbMenuItemID);
            
            lblName = new JLabel("Menu item name:");
            lblName.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbLayout.setConstraints(lblName, gbc);
            this.add(lblName);
            
            tbName = new JTextField(20);
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbLayout.setConstraints(tbName, gbc);
            this.add(tbName);
            
            lblPrice = new JLabel("Menu item price:");
            lblPrice.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbLayout.setConstraints(lblPrice, gbc);
            this.add(lblPrice);
            
            tbPrice = new JTextField(10);
            tbPrice.setInputVerifier(new DoubleInputVerifier(1,10000));
            gbc.gridx = 1;
            gbc.gridy = 2;
            gbLayout.setConstraints(tbPrice, gbc);
            this.add(tbPrice);
            
            lblType = new JLabel("Menu item type:");
            lblType.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbLayout.setConstraints(lblType, gbc);
            this.add(lblType);
            
            String[] combodata = {"Main", "Drink", "Alcohol", "Dessert"};
            cbType = new JComboBox(combodata);
            gbc.gridx = 1;
            gbc.gridy = 3;
            gbLayout.setConstraints(cbType, gbc);
            this.add(cbType);
            
            btnOK = new JButton("OK");
            btnOK.addActionListener(this);
            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.gridwidth = 2;
            gbLayout.setConstraints(btnOK, gbc);
            this.add(btnOK);
        }
        
        private void setMenuID(String id)
        {
            tbMenuItemID.setText(id);
        }
        
        private void setItemName(String name)
        {
            tbName.setText(name);
        }
        
        private void setPrice(String price)
        {
            tbPrice.setText(price);
        }
        
        private void setType(String type)
        {
            cbType.setSelectedItem(type);
        }
        
        
        public void init(int menuItemID)
        {
            //------------- Add new menu item ------------
            if( menuItemID == 0)    
            {
                setMenuID("");
                tbMenuItemID.setEditable(true);
                setItemName("");
                setPrice("");
                setType("Main");
                isUpdate = false;
                return;
            }
            
            //------------- Update menu item ------------
            
            MenuItem   rMenuItem = rcController.getMenuItemData(menuItemID);
            isUpdate = true;
            
            if( rMenuItem == null)
            {
                showErrorDialog("Error", "Get menu item data failed.");
                setItemName("");
                setPrice("");
                setType("Main");
                return;
            }
            setMenuID(Integer.toString(rMenuItem.getID()));
            setItemName(rMenuItem.getName());
            setPrice(Double.toString(rMenuItem.getPrice()));
            tbPrice.setBackground( UIManager.getColor( "TextField.background" ) ); 
            switch( rMenuItem.getType())
            {
                case MenuItem.MAIN:
                    setType("Main");
                break;
                case MenuItem.DRINK:
                    setType("Drink");
                break;
                case MenuItem.ALCOHOL:
                    setType("Alcohol");
                break;
                case MenuItem.DESSERT:
                    setType("Dessert");
                break;
            }
            tbMenuItemID.setEditable(false);
            tbMenuItemID.setBackground( UIManager.getColor( "TextField.background" ) ); 
        }
        
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == btnOK)
            {
                //if( !inputVerified) return;
                //Check whether current focuced compornent have to verify their value
                if (btnOK.getVerifyInputWhenFocusTarget()) {
                    //Try to get focus
                    btnOK.requestFocusInWindow();
                    if (!btnOK.hasFocus()) {    //Can not get focus ?� the compornent have not been verified
                        return;
                    }
                }  
                
                if( tbMenuItemID.getText().equals("") || tbName.getText().equals("") || tbPrice.getText().equals(""))
                {
                    displayErrorMessage("Fill all form!!");
                    return;
                }

                int menuItemID = Integer.parseInt(tbMenuItemID.getText());
 
                String strMenuType = (String)cbType.getSelectedItem();
                byte    menuType;
                
                if( strMenuType.equals("Main"))
                {
                    menuType = MenuItem.MAIN;
                }
                else if( strMenuType.equals("Drink"))
                {
                    menuType = MenuItem.DRINK;
                }
                else if( strMenuType.equals("Alcohol"))
                {
                    menuType = MenuItem.ALCOHOL;
                }
                else    //Dessert
                {
                    menuType = MenuItem.DESSERT;
                }
                
                if(isUpdate)
                {
                    if(! rcController.updateMenuItem(menuItemID , tbName.getText(), Double.parseDouble(tbPrice.getText()), menuType))
                    {
                        showErrorDialog("Error", rcController.getErrorMessage());
                        return;
                    }
                    showConfirmDialog("Message", "Update successful!!");
                }
                else
                {                   
                    if(! rcController.addNewMenuItem(menuItemID , tbName.getText(), Double.parseDouble(tbPrice.getText()), menuType))
                    {
                        showErrorDialog("Error", rcController.getErrorMessage());
                        return;
                    }
                    showConfirmDialog("Message", "New menu item is added!!");
                }
                init(menuItemID);
            }
        }
    }
    
    /****************************************************************
     * Employee list panel
    *****************************************************************/       
    private class EmployeeListPanel extends JPanel implements ActionListener
    {
        private JScrollPane     scrollPanel;
        private JList           displayList;
        //private JPanel          btnPanel;
        private JButton         btnAddStaff;
        private JButton         btnEditStaff;
        private JButton         btnDeleteStaff;
        private JButton         btnClockOut;
        
        public EmployeeListPanel()
        {
            GridBagLayout gbLayout = new GridBagLayout();
            this.setLayout( gbLayout);
            GridBagConstraints gbc = new GridBagConstraints();

            scrollPanel = new JScrollPane();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.gridwidth = 4;
            gbLayout.setConstraints(scrollPanel, gbc);
            this.add(scrollPanel);
            
            btnAddStaff     = new JButton("Add new staff");
            btnAddStaff.addActionListener(this);
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.weighty = 0;
            gbc.weightx = 0.25;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbLayout.setConstraints(btnAddStaff, gbc);
            this.add(btnAddStaff);
            
            btnEditStaff    = new JButton("Edit staff");
            btnEditStaff.addActionListener(this);
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbLayout.setConstraints(btnEditStaff, gbc);
            this.add(btnEditStaff);
            
            btnDeleteStaff   = new JButton("Delete staff");
            btnDeleteStaff.addActionListener(this);
            gbc.gridx = 2;
            gbc.gridy = 1;
            gbLayout.setConstraints(btnDeleteStaff, gbc);
            this.add(btnDeleteStaff);
            
            btnClockOut  = new JButton("Clock out");
            btnClockOut.addActionListener(this);
            gbc.gridx = 3;
            gbc.gridy = 1;
            gbLayout.setConstraints(btnClockOut, gbc);
            this.add(btnClockOut);
            
            displayList = new JList();
            displayList.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));
            displayList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        }
        
        public void init()
        {
            showStaffList();
        }
        
        public void showStaffList()
        {
            displayList.setListData(rcController.createStaffList().toArray());
            scrollPanel.getViewport().setView(displayList);
        }
        
        private int getSelectedStaffID()
        {
            String orderLine = (String)displayList.getSelectedValue();
            if (orderLine == null)
                return -1;
                
            return getIDfromString( orderLine, 4);
        }
        
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == btnAddStaff)
            {
                cEditEmployeePanel.init(0);
                changeMainPanel("EditEmployee");
            }
            else if (ae.getSource() == btnEditStaff)
            {
                int staffID = getSelectedStaffID();
                if( staffID == -1)    return;
                 cEditEmployeePanel.init(staffID);
                 changeMainPanel("EditEmployee");
            }
            else if (ae.getSource() == btnDeleteStaff)
            {
                int deleteStaffID = getSelectedStaffID();
                if( deleteStaffID == -1)    return;
                
                if( showYesNoDialog("", "Are you sure to delete the staff?") == DIALOG_YES)
                {
                    if(!rcController.deleteStaff(deleteStaffID))
                    {
                        showErrorDialog("Error", rcController.getErrorMessage());
                    }
                    else
                    {
                        displayMessage("Deleted.");
                        init();
                    }
                }
            }
            else if (ae.getSource() == btnClockOut)
            {
                int staffID = getSelectedStaffID();
                if( staffID == -1)    return;
                if(showYesNoDialog("", "Are you sure to make the staff clocked out?") == DIALOG_NO)
                    return;
                if( rcController.clockOut(staffID) == false)
                    showErrorDialog("Error", rcController.getErrorMessage());
                else
                {
                    displayMessage("The staff have been clocked out.");
                    init();
                }
            }
        }
    }
    
    /****************************************************************
     * Edit employee panel
    *****************************************************************/       
    private class EditEmployeePanel extends JPanel implements ActionListener
    {
        private JLabel          lblStaffID;
        private JTextField      tbStaffID;
        private JLabel          lblFirstName;
        private JTextField      tbFirstName;
        private JLabel          lblLastName;
        private JTextField      tbLastName;
        private JLabel          lblPassword;
        private JPasswordField  tbPassword;
        private JButton         btnOK;
        
        private boolean         isUpdate;
        
        public EditEmployeePanel()
        {
            GridBagLayout gbLayout = new GridBagLayout();
            this.setLayout( gbLayout);
            GridBagConstraints gbc = new GridBagConstraints();
            
            lblStaffID = new JLabel("StaffID:");
            lblStaffID.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            gbLayout.setConstraints(lblStaffID, gbc);
            this.add(lblStaffID);
            
            tbStaffID = new JTextField(4);
            tbStaffID.setInputVerifier(new IntegerInputVerifier(1,10000));
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbLayout.setConstraints(tbStaffID, gbc);
            this.add(tbStaffID);
            
            lblFirstName = new JLabel("FirstName:");
            lblFirstName.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbLayout.setConstraints(lblFirstName, gbc);
            this.add(lblFirstName);
            
            tbFirstName = new JTextField(20);
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbLayout.setConstraints(tbFirstName, gbc);
            this.add(tbFirstName);
            
            lblLastName = new JLabel("LastName:");
            lblLastName.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbLayout.setConstraints(lblLastName, gbc);
            this.add(lblLastName);
            
            tbLastName = new JTextField(20);
            gbc.gridx = 1;
            gbc.gridy = 2;
            gbLayout.setConstraints(tbLastName, gbc);
            this.add(tbLastName);
            
            lblPassword = new JLabel("Password:");
            lblPassword.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbLayout.setConstraints(lblPassword, gbc);
            this.add(lblPassword);
            
            tbPassword = new JPasswordField(20);
            gbc.gridx = 1;
            gbc.gridy = 3;
            gbLayout.setConstraints(tbPassword, gbc);
            this.add(tbPassword);
            
            btnOK = new JButton("OK");
            btnOK.addActionListener(this);
            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.gridwidth = 2;
            gbLayout.setConstraints(btnOK, gbc);
            this.add(btnOK);
        }
        
        private void setUserID(int id)
        {
            tbStaffID.setText(Integer.toString(id));
        }
        
        private void setPassword(String password)
        {
            tbPassword.setText(password);
        }
        
        private void setLastName(String lastName)
        {
            tbLastName.setText(lastName);
        }
        
        private void setFirstName(String firstName)
        {
            tbFirstName.setText(firstName);
        }
        
        public void init(int employeeID)
        {
            //------------- Add new staff ------------
            if( employeeID == 0)    
            {
                setUserID(0);
                tbStaffID.setEditable(true);
                setPassword("");
                setLastName("");
                setFirstName("");
                isUpdate = false;
                return;
            }
            
            //------------- Update staff ------------
            
            Staff   rStaff = rcController.getStaffData(employeeID);
            isUpdate = true;
            
            if( rStaff == null)
            {
                showErrorDialog("Error", "Get staff data failed.");
                setLastName("");
                setFirstName("");
                return;
            }
            setUserID(rStaff.getID());
            setPassword(rStaff.getPassword());
            setLastName(rStaff.getLastName());
            setFirstName(rStaff.getFirstName());
            tbStaffID.setEditable(false);
            tbStaffID.setBackground( UIManager.getColor( "TextField.background" ) ); 
        }
        
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == btnOK)
            {
                //if( !inputVerified) return;
                //Check whether current focuced compornent have to verify their value
                if (btnOK.getVerifyInputWhenFocusTarget()) {
                    //Try to get focus
                    btnOK.requestFocusInWindow();
                    if (!btnOK.hasFocus()) {    //Can not get focus ?� the compornent have not been verified
                        return;
                    }
                }  
                
                
                int test = tbPassword.getPassword().length;
                
                if(tbPassword.getPassword().length == 0 || tbFirstName.getText().equals("") || tbLastName.getText().equals(""))
                {
                    displayErrorMessage("Fill all form!!");
                    return;
                }
                
                int staffID = Integer.parseInt(tbStaffID.getText());
                
                if(isUpdate)
                {
                    if(! rcController.updateStaff(staffID , new String(tbPassword.getPassword()), tbFirstName.getText(), tbLastName.getText())) 
                    {
                        showErrorDialog("Error", rcController.getErrorMessage());
                        return;
                    }
                    showConfirmDialog("Message", "Update successful!!");
                }
                else
                {
                    boolean isManager = false;
                    
                    if( showYesNoDialog("", "Add as Manager?") == DIALOG_YES)
                        isManager = true;
                    
                    if(!rcController.addNewStaff(staffID,
                                                new String(tbPassword.getPassword()),
                                                tbFirstName.getText(),
                                                tbLastName.getText(),
                                                isManager))
                    {
                        showErrorDialog("Error", rcController.getErrorMessage());
                        return;
                    }
                    showConfirmDialog("Message", "New staff is added!!");
                    
                }
                init(staffID);
            }
        }
    }

    /****************************************************************
     * Order list panel
     *****************************************************************/
    private class OrderListPanel extends JPanel implements ActionListener {
        private JScrollPane scrollPanel;
        private JButton btnNewOrder;
        private JButton btnEditOrder;
        private JButton btnCloseOrder;
        private JButton btnCancelOrder;
        private JLabel lblTotalSales;
        private JLabel lblTotalCount;
        private JLabel lblCancelTotal;
        private JLabel lblCancelCount;
        private JList displayList;

        public OrderListPanel() {
            GridBagLayout gbLayout = new GridBagLayout();
            this.setLayout(gbLayout);
            GridBagConstraints gbc = new GridBagConstraints();

            scrollPanel = new JScrollPane();
            scrollPanel.setPreferredSize(new Dimension(500, 300));
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 4;
            gbLayout.setConstraints(scrollPanel, gbc);
            this.add(scrollPanel);

            lblTotalCount = new JLabel();
            lblTotalCount.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 2;
            gbc.insets = new Insets(10, 10, 10, 10);
            gbLayout.setConstraints(lblTotalCount, gbc);
            this.add(lblTotalCount);

            lblTotalSales = new JLabel();
            lblTotalSales.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
            gbc.gridx = 2;
            gbc.gridy = 1;
            gbc.gridwidth = 2;
            gbLayout.setConstraints(lblTotalSales, gbc);
            this.add(lblTotalSales);

            lblCancelCount = new JLabel();
            lblCancelCount.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 2;
            gbLayout.setConstraints(lblCancelCount, gbc);
            this.add(lblCancelCount);

            lblCancelTotal = new JLabel();
            lblCancelTotal.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
            gbc.gridx = 2;
            gbc.gridy = 2;
            gbc.gridwidth = 2;
            gbLayout.setConstraints(lblCancelTotal, gbc);
            this.add(lblCancelTotal);

            btnNewOrder = new JButton("New");
            btnNewOrder.addActionListener(this);
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.gridwidth = 1;
            gbc.weightx = 0.25;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbLayout.setConstraints(btnNewOrder, gbc);
            this.add(btnNewOrder);

            btnEditOrder = new JButton("Edit");
            btnEditOrder.addActionListener(this);
            gbc.gridx = 1;
            gbc.gridy = 3;
            gbLayout.setConstraints(btnEditOrder, gbc);
            this.add(btnEditOrder);

            btnCloseOrder = new JButton("Close");
            btnCloseOrder.addActionListener(this);
            gbc.gridx = 2;
            gbc.gridy = 3;
            gbLayout.setConstraints(btnCloseOrder, gbc);
            this.add(btnCloseOrder);

            btnCancelOrder = new JButton("Cancel");
            btnCancelOrder.addActionListener(this);
            gbc.gridx = 3;
            gbc.gridy = 3;
            gbLayout.setConstraints(btnCancelOrder, gbc);
            this.add(btnCancelOrder);

            displayList = new JList();
        }

        public void init() {
            showOrderList();
        }

        private void showOrderList() {
            displayList.setListData(rcController.createOrderList().toArray());
            scrollPanel.getViewport().setView(displayList);
            setTotalCount(rcController.getTodaysOrderCnt());
            setTotalSales(rcController.getTotalSales());
            setCancelCount(rcController.getTodaysCancelCnt());
            setCancelTotal(rcController.getCancelTotal());
        }

        private int getSelectedOrderID() {
            String orderLine = (String) displayList.getSelectedValue();
            if (orderLine == null) return -1;
            return getIDfromString(orderLine, 4);
        }

        private String getSelectedOrderStaffName() {
            String stringLine = (String) displayList.getSelectedValue();
            if (stringLine == null) return null;

            int index = stringLine.indexOf("Name:");
            if (index == -1) {
                showErrorDialog("Error", "String 'Name:' is not found!!");
                return null;
            }

            String staffName = stringLine.substring(index + 5, index + 5 + 22);
            return staffName.trim();
        }

        private void setTotalCount(int count) {
            lblTotalCount.setText("Today's order: " + count);
        }

        private void setTotalSales(double sales) {
            lblTotalSales.setText("Total: $ " + sales);
        }

        private void setCancelCount(int count) {
            lblCancelCount.setText("Canceled orders: " + count);
        }

        private void setCancelTotal(double sales) {
            lblCancelTotal.setText("Cancel total: $ " + sales);
        }

        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == btnNewOrder) {
                changeMainPanel("OrderDetail");
                int orderID = rcController.createOrder();
                String staffName = rcController.getCurrentUserName();
                cOrderDetailPanel.init(orderID, staffName);
            } else if (ae.getSource() == btnEditOrder) {
                int orderID = getSelectedOrderID();
                String staffName = getSelectedOrderStaffName();
                if (orderID == -1) return;

                changeMainPanel("OrderDetail");
                cOrderDetailPanel.init(orderID, staffName);
            } else if (ae.getSource() == btnCloseOrder) {
                int orderID = getSelectedOrderID();
                if (orderID == -1) return;

                if (showYesNoDialog("Close order", "Are you sure to close the order?") == DIALOG_YES) {
                    if (!rcController.closeOrder(orderID))
                        displayErrorMessage(rcController.getErrorMessage());
                    showOrderList();
                }
            } else if (ae.getSource() == btnCancelOrder) {
                int orderID = getSelectedOrderID();
                if (orderID == -1) return;

                if (showYesNoDialog("Cancel order", "Are you sure to cancel the order?") == DIALOG_YES) {
                    if (!rcController.cancelOrder(orderID))
                        displayErrorMessage(rcController.getErrorMessage());
                    showOrderList();
                }
            }
        }
    }

    /****************************************************************
     * Order detail panel
    *****************************************************************/
    private class OrderDetailPanel extends JPanel implements ActionListener, ListSelectionListener
    {
        //Right
        private JLabel          lblRightTitle;

        private JScrollPane     menuScrollPanel;
        private JButton         btnAll;
        private JButton         btnMain;
        private JButton         btnDrink;
        private JButton         btnAlcohol;
        private JButton         btnDessert;

        //Left
        private JLabel          lblLeftTitle;
        private JLabel          lblLeftInfo;
        private JScrollPane     orderScrollPanel;
        private JPanel          btnPanel;
        private JButton         btnAddItem;
        private JButton         btnDeleteItem;
        private JLabel          lblQuantity;
        private JTextField      tfQuantity;

        //Added new custom button
        private JButton         customButton;

        private JLabel              lblTotalSales;
        private JLabel              lblOrderState;
        private JLabel              lblStaffName;
        private JList               orderItemList;
        private JList               menuList;

        private int             currentOrderID;
        private int             orderItemCnt;
        private int             currentOrderState;

        private JPanel          orderDetailPanel;
        private JPanel          menuListPanel;


        /// @class OrderDetailPanel
        /// @brief
        /// This class displays the order details, item list, and available menu items.
        /// Added options to customize pizza to add addtional topping
        /// @ author
        /// Alex Ratchev
        public OrderDetailPanel()
        {
            this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
            orderDetailPanel = new JPanel();
            GridBagLayout gbLayout = new GridBagLayout();
            orderDetailPanel.setLayout(gbLayout);
            GridBagConstraints gbc = new GridBagConstraints();

            lblLeftTitle = new JLabel("Order detail");
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 4;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.insets = new Insets(5, 5, 5, 5);
            gbLayout.setConstraints(lblLeftTitle, gbc);
            orderDetailPanel.add(lblLeftTitle);

            lblLeftInfo = new JLabel("No  Item name                 quantity    price");
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 4;
            gbLayout.setConstraints(lblLeftInfo, gbc);
            orderDetailPanel.add(lblLeftInfo);

            orderScrollPanel = new JScrollPane();
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.ipadx = 0;
            gbc.ipady = 0;
            gbc.weighty = 1.0;
            gbLayout.setConstraints(orderScrollPanel, gbc);
            orderDetailPanel.add(orderScrollPanel);

            lblTotalSales = new JLabel();
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.weighty = 0;
            gbc.gridwidth = 4;
            gbLayout.setConstraints(lblTotalSales, gbc);
            orderDetailPanel.add(lblTotalSales);

            lblOrderState = new JLabel();
            gbc.gridx = 0;
            gbc.gridy = 4;
            gbLayout.setConstraints(lblOrderState, gbc);
            orderDetailPanel.add(lblOrderState);

            lblStaffName = new JLabel();
            gbc.gridx = 0;
            gbc.gridy = 5;
            gbc.gridwidth = 4;
            gbLayout.setConstraints(lblStaffName, gbc);
            orderDetailPanel.add(lblStaffName);

            lblQuantity = new JLabel("Quantity");
            gbc.ipadx = 20;
            gbc.gridx = 0;
            gbc.gridy = 6;
            gbc.gridwidth = 2;
            gbLayout.setConstraints(lblQuantity, gbc);
            orderDetailPanel.add(lblQuantity);

            tfQuantity = new JTextField();
            tfQuantity.setInputVerifier(new IntegerInputVerifier(1,100));
            tfQuantity.addActionListener(this);
            gbc.gridx = 0;
            gbc.gridy = 7;
            gbLayout.setConstraints(tfQuantity, gbc);
            orderDetailPanel.add(tfQuantity);

            btnAddItem  = new JButton("Add");
            btnAddItem.addActionListener(this);
            gbc.gridx = 2;
            gbc.gridy = 6;
            gbc.gridwidth = 1;
            gbc.gridheight = 2;
            gbLayout.setConstraints(btnAddItem, gbc);
            orderDetailPanel.add(btnAddItem);

            btnDeleteItem   = new JButton("Delete");
            btnDeleteItem.addActionListener(this);
            gbc.gridx = 3;
            gbc.gridy = 6;
            gbLayout.setConstraints(btnDeleteItem, gbc);
            orderDetailPanel.add(btnDeleteItem);


             ///@brief Custom button to trigger pizza topping customization.
             ///Clicking it opens a new window with topping options.
             ///@author Alex Ratchev
            customButton = new JButton("Customize Pizza");
            customButton.setVisible(true);
            customButton.setFont(new Font("Arial", Font.BOLD, 14));
            customButton.setBackground(new Color(255, 230, 150)); // Light yellow
            customButton.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            customButton.setPreferredSize(new Dimension(200, 40)); // bigger size

           /// @brief This will display the topping selection window with available checkboxes
           /// Once topping are selected and saved, a message is shown
           /// @author Alex Ratchev
            customButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFrame toppingsFrame = new JFrame("Customize Your Pizza");
                    toppingsFrame.setSize(400, 300);
                    Window parentWindow = SwingUtilities.getWindowAncestor(orderDetailPanel);
                    if (parentWindow != null) {
                        toppingsFrame.setLocationRelativeTo(parentWindow);
                    } else {
                        toppingsFrame.setLocationRelativeTo(null); // fallback to center screen
                    }
                    toppingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    toppingsFrame.setLayout(new BorderLayout());

                    JPanel toppingsPanel = new JPanel();
                    toppingsPanel.setLayout(new BoxLayout(toppingsPanel, BoxLayout.Y_AXIS));
                    toppingsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                    // Add topping checkboxes
                    JCheckBox cbPepperoni = new JCheckBox("Pepperoni");
                    JCheckBox cbMushrooms = new JCheckBox("Mushrooms");
                    JCheckBox cbCheese = new JCheckBox("Extra Cheese");
                    JCheckBox cbOlives = new JCheckBox("Olives");
                    JCheckBox cbBacon = new JCheckBox("Bacon");

                    toppingsPanel.add(cbPepperoni);
                    toppingsPanel.add(cbMushrooms);
                    toppingsPanel.add(cbCheese);
                    toppingsPanel.add(cbOlives);
                    toppingsPanel.add(cbBacon);


                    // Click add to finalize the pizza after toppings selected
                    JButton btnAdd = new JButton("Create");
                    btnAdd.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent ev) {
                            // Simulate clicking the actual Add button for pizza
                            Pizza pizza = new PlainPizza(); // Assuming you have a Plain Pizza class
                            if(toppingsFrame.isVisible()){
                                if(cbPepperoni.isSelected()) {
                                    pizza = new DecoratorPepperoni(pizza);
                                }
                                if(cbMushrooms.isSelected()) {
                                    pizza = new DecoratorMushroom(pizza);
                                }
                                if(cbCheese.isSelected()) {
                                    pizza = new DecoratorExtraCheese(pizza);
                                }
                                if(cbOlives.isSelected()) {
                                    pizza = new DecoratorOlives(pizza);
                                }
                                if(cbBacon.isSelected()) {
                                    pizza = new DecoratorBacon(pizza);
                                }
                                rcController.setPizza(pizza);
                            }
                            btnAddItem.doClick();
                            
                            
                            // Close the toppings window
                            toppingsFrame.dispose();
                        }
                    });

                    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                    buttonPanel.add(btnAdd);

                    toppingsFrame.add(toppingsPanel, BorderLayout.CENTER);
                    toppingsFrame.add(buttonPanel, BorderLayout.SOUTH);
                    toppingsFrame.setVisible(true);
                }
            });


            gbc.gridx = 0;
            gbc.gridy = 8;
            gbc.gridwidth = 4;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(10, 5, 10, 5); // more spacing
            gbc.weighty = 0;
            gbLayout.setConstraints(customButton, gbc);
            orderDetailPanel.add(customButton);

            //Right panel
            menuListPanel = new JPanel();
            menuListPanel.setLayout(gbLayout);

            lblRightTitle = new JLabel("Menu list");
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.ipadx = 0;
            gbc.gridwidth = 5;
            gbc.gridheight = 1;
            gbc.fill = GridBagConstraints.BOTH;
            gbLayout.setConstraints(lblRightTitle, gbc);
            menuListPanel.add(lblRightTitle);

            menuScrollPanel = new JScrollPane();
            gbc.gridy = 1;
            gbc.weighty = 1.0;
            gbLayout.setConstraints(menuScrollPanel, gbc);
            menuListPanel.add(menuScrollPanel);

            btnAll  = new JButton("All");
            btnAll.addActionListener(this);
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 1;
            gbc.weighty = 0;
            gbc.fill = GridBagConstraints.BOTH;
            gbLayout.setConstraints(btnAll, gbc);
            menuListPanel.add(btnAll);

            btnMain  = new JButton("Main");
            btnMain.addActionListener(this);
            gbc.gridx = 1;
            gbc.gridy = 2;
            gbLayout.setConstraints(btnMain, gbc);
            menuListPanel.add(btnMain);

            btnDrink  = new JButton("Drink");
            btnDrink.addActionListener(this);
            gbc.gridx = 2;
            gbc.gridy = 2;
            gbLayout.setConstraints(btnDrink, gbc);
            menuListPanel.add(btnDrink);

            btnAlcohol  = new JButton("Alcohol");
            btnAlcohol.addActionListener(this);
            gbc.gridx = 3;
            gbc.gridy = 2;
            gbLayout.setConstraints(btnAlcohol, gbc);
            menuListPanel.add(btnAlcohol);

            btnDessert  = new JButton("Dessert");
            btnDessert.addActionListener(this);
            gbc.gridx = 4;
            gbc.gridy = 2;
            gbLayout.setConstraints(btnDessert, gbc);
            menuListPanel.add(btnDessert);

            LineBorder border = new LineBorder(Color.BLACK, 1, false);
            menuListPanel.setBorder(border);
            orderDetailPanel.setBorder(border);
            orderDetailPanel.setBackground(new Color(220, 230, 240)); // light gray-blue
            customButton.setBackground(new Color(180, 200, 255));     // soft blue
            customButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            customButton.setFont(new Font("Arial", Font.BOLD, 12));
            this.add(orderDetailPanel);
            this.add(menuListPanel);

            orderItemList   = new JList();
            orderItemList.setFont(new Font(Font.MONOSPACED,Font.PLAIN,10));
            orderItemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            menuList = new JList();
            menuList.addListSelectionListener(this);
            menuList.setFont(new Font(Font.MONOSPACED,Font.PLAIN,10));
            menuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        }
        
        public void init(int orderID, String staffName)
        {
            currentOrderID = orderID;
            currentOrderState = rcController.getOrderState(orderID);
            switch(currentOrderState)
            {
                case Order.ORDER_CLOSED:
                    setOrderState("Closed");
                break;
                case Order.ORDER_CANCELED:
                    setOrderState("Canceled");
                break;
                default:
                break;
            }
            
             if(currentOrderState != 0)
            {
                btnAddItem.setEnabled(false);
                btnDeleteItem.setEnabled(false);
            }
            else
            {
                btnAddItem.setEnabled(true);
                btnDeleteItem.setEnabled(true);
            }
            
            refleshOrderDetailList();
            menuList.setListData(rcController.createMenuList(0).toArray());
            menuScrollPanel.getViewport().setView(menuList);
            tfQuantity.setText("");
            tfQuantity.setBackground( UIManager.getColor( "TextField.background" ) );
            setStaffName(staffName);
        }
        
        private void setTotal(double total)
        {
            lblTotalSales.setText("Total charge: $" + total);
        }
        
        private void setOrderState(String state)
        {
            lblOrderState.setText("Order state: " + state);
        }
        
        private void setStaffName(String name)
        {
            lblStaffName.setText("Staff name: " + name);
        }
        
        private void refleshOrderDetailList()
        {
            ArrayList<String> list = rcController.createOrderItemlList(currentOrderID);
            setTotal(rcController.getOrderTotalCharge(currentOrderID));
            orderItemCnt = list.size();
            orderItemList.setListData(list.toArray());
            //createOrderItemlList(currentOrderID, orderItemList);
            orderScrollPanel.getViewport().setView(orderItemList);
        }
        
        private int getOrderDetailIndexFromString(String orderLine)
        {
            try
            {
                String strIndex = orderLine.substring(0, 4);
                int index = Integer.parseInt(strIndex.trim());
                return index;
            }
            catch(Exception e)
            {
                //showErrorDialog("Error", "Parse error");
                return -1;
            }
        }
        
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == btnAddItem)
            {
                //if(!inputVerified)
                //    return;
                //Check whether current focuced compornent have to verify their value
                if (btnAddItem.getVerifyInputWhenFocusTarget()) {
                    //Try to get focus
                    btnAddItem.requestFocusInWindow();
                    if (!btnAddItem.hasFocus()) {    //Can not get focus ?� the compornent have not been verified
                        return;
                    }
                }  
                
                String menuLine = (String)menuList.getSelectedValue();
                if (menuLine == null)
                    return;

                int     id = getIDfromString( menuLine, 4);
                if(id == -1)
                    return;
                if( tfQuantity.getText().equals(""))
                {
                    showErrorDialog("Error", "Enter quantity!!");
                    return;
                }
                byte    quantity = Byte.parseByte(tfQuantity.getText().trim());
                /*if( quantity <= 0 || 100 <= quantity)
                {
                    displayErrorMessage("Quantity must be between 1 and 100");
                    return;
                }*/
                displayMessage("Menu ID = "+ id + " Quantity = " + quantity);
                if( rcController.addNewOrderItem(currentOrderID, id, quantity) == false)
                {
                    displayErrorMessage("addNewOrderItem Error!!\n" + rcController.getErrorMessage());
                }
                refleshOrderDetailList();
                //auto scroll
                orderItemList.ensureIndexIsVisible(orderItemCnt-1);
                
            }
            else if (ae.getSource() == btnDeleteItem)
            {
                String orderLine = (String)orderItemList.getSelectedValue();
                if(orderLine == null)
                    return;
                    
                int     index = getOrderDetailIndexFromString(orderLine);
                if(index == -1)
                    return;
                if( rcController.deleteOrderItem(currentOrderID, index) == false)
                {
                    displayErrorMessage("deleteOrderItem Error!!\n" + rcController.getErrorMessage());
                }
                refleshOrderDetailList();
            }
             else if (ae.getSource() == btnAll)
            {
                menuList.setListData(rcController.createMenuList(0).toArray());
                menuScrollPanel.getViewport().setView(menuList);
            }
             else if (ae.getSource() == btnMain)
            {
                //createParticularMenuList(MenuItem.MAIN, menuList);
                menuList.setListData(rcController.createMenuList(MenuItem.MAIN).toArray());
                menuScrollPanel.getViewport().setView(menuList);
            }
             else if (ae.getSource() == btnDrink)
            {
                //createParticularMenuList(MenuItem.DRINK, menuList);
                menuList.setListData(rcController.createMenuList(MenuItem.DRINK).toArray());
                menuScrollPanel.getViewport().setView(menuList);
            }
             else if (ae.getSource() == btnAlcohol)
            {
                //createParticularMenuList(MenuItem.ALCOHOL, menuList);
                menuList.setListData(rcController.createMenuList(MenuItem.ALCOHOL).toArray());
                menuScrollPanel.getViewport().setView(menuList);
            }
             else if (ae.getSource() == btnDessert)
            {
                //createParticularMenuList(MenuItem.DESSERT, menuList);
                menuList.setListData(rcController.createMenuList(MenuItem.DESSERT).toArray());
                menuScrollPanel.getViewport().setView(menuList);
            }
        }

        // Updated method will dynamically show or hide the Custom button depending on the selection
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting() && e.getSource() == menuList) {
                tfQuantity.setText("1");

                String selectedLine = (String) menuList.getSelectedValue();
                if (selectedLine != null) {
                    int menuID = getIDfromString(selectedLine, 4);

                    // Only show Customize button for pizza (menu ID 101)
                    customButton.setVisible(menuID == 101);
                } else {
                    customButton.setVisible(false); // Hide if nothing is selected
                }

                customButton.revalidate();
                customButton.repaint();
            }
        }
    }

    /****************************************************************
     * Total sales panel
    *****************************************************************/   
    private class TotalSalesPanel extends JPanel implements ActionListener
    {
        private JScrollPane     scrollPanel;
        private JList           displayList;
        private JButton         btnPrint;
        private JButton         btnCloseAllOrder;
        private JLabel          lblTotalSales;
        private JLabel          lblTotalCount;
        private JLabel          lblCancelTotal;
        private JLabel          lblCancelCount;
        
        
        public TotalSalesPanel()
        {
            GridBagLayout gbLayout = new GridBagLayout();
            this.setLayout( gbLayout);
            GridBagConstraints gbc = new GridBagConstraints();

            scrollPanel = new JScrollPane();
            //scrollPanel.setPreferredSize(new Dimension(500, 300));
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 4;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.BOTH;
            gbLayout.setConstraints(scrollPanel, gbc);
            this.add(scrollPanel);
            
            lblTotalCount = new JLabel();
            lblTotalCount.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 2;
            gbc.weighty = 0;
            gbLayout.setConstraints(lblTotalCount, gbc);
            this.add(lblTotalCount);
            
            lblTotalSales = new JLabel();
            lblTotalSales.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));
            gbc.gridx = 2;
            gbc.gridy = 1;
            gbLayout.setConstraints(lblTotalSales, gbc);
            this.add(lblTotalSales);
            
            lblCancelCount = new JLabel();
            lblCancelCount.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbLayout.setConstraints(lblCancelCount, gbc);
            this.add(lblCancelCount);
            
            lblCancelTotal = new JLabel();
            lblCancelTotal.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));
            gbc.gridx = 2;
            gbc.gridy = 2;
            gbLayout.setConstraints(lblCancelTotal, gbc);
            this.add(lblCancelTotal);
            
            btnPrint    = new JButton("Generate text file");
            btnPrint.addActionListener(this);
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.gridwidth = 2;
            gbLayout.setConstraints(btnPrint, gbc);
            this.add(btnPrint);
            
            btnCloseAllOrder    = new JButton("Close all order");
            btnCloseAllOrder.addActionListener(this);
            gbc.gridx = 2;
            gbc.gridy = 3;
            gbLayout.setConstraints(btnCloseAllOrder, gbc);
            this.add(btnCloseAllOrder);
            
            displayList = new JList();
        }
        
        private void setTotalCount( int count)
        {
            lblTotalCount.setText("Today's order: " + count);
        }
        
        private void setTotalSales( double sales)
        {
            lblTotalSales.setText("Total:$ " + sales);
        }
        
        private void setCancelCount( int count)
        {
            lblCancelCount.setText("Canceled orders: " + count);
        }
        
        private void setCancelTotal( double sales)
        {
            lblCancelTotal.setText("Cancel total:$ " + sales);
        }
        
        private void showOrderList()
        {
            displayList.setListData(rcController.createOrderList().toArray());
            scrollPanel.getViewport().setView(displayList);
            
            setTotalCount(rcController.getTodaysOrderCnt());
            setTotalSales(rcController.getTotalSales());
            setCancelCount(rcController.getTodaysCancelCnt());
            setCancelTotal(rcController.getCancelTotal());
        }
        
        public void init()
        {
            showOrderList();
        }
        
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == btnPrint)
            {
                String createFineName = rcController.generateSalesReport();
                if( createFineName == null)
                    displayErrorMessage(rcController.getErrorMessage());
                else
                    displayMessage(createFineName + " have been generated.");
            }
            else if (ae.getSource() == btnCloseAllOrder)
            {
                if (showYesNoDialog("", "Are you sure to close all order?") == DIALOG_YES)
                {
                    rcController.closeAllOrder();
                    init();
                    displayMessage("");
                }
            }
        }
    }
    
    /****************************************************************
     * Payment panel
    *****************************************************************/   
    private class PaymentPanel extends JPanel implements ActionListener
    {
        private JScrollPane     scrollPanel;
        private JTextArea       displayArea;
        private JButton         btnPrint;
        private JButton         btnAllClockOut;
        
        public PaymentPanel()
        {
            GridBagLayout gbLayout = new GridBagLayout();
            this.setLayout( gbLayout);
            GridBagConstraints gbc = new GridBagConstraints();

            displayArea = new JTextArea();
            displayArea.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));
            displayArea.setEditable(false);
            displayArea.setMargin(new Insets(5, 5, 5, 5));
            scrollPanel = new JScrollPane(displayArea);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.BOTH;
            gbLayout.setConstraints(scrollPanel, gbc);
            this.add(scrollPanel);
            
            btnPrint = new JButton("Create payment report file");
            btnPrint.addActionListener(this);
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.weighty = 0;
            gbLayout.setConstraints(btnPrint, gbc);
            this.add(btnPrint);
            
            btnAllClockOut = new JButton("Clock out for all staff");
            btnAllClockOut.addActionListener(this);
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.weighty = 0;
            gbLayout.setConstraints(btnAllClockOut, gbc);
            this.add(btnAllClockOut);
        }
        

        
        public void init()
        {
            displayArea.setText(rcController.createPaymentList());
        }
        
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == btnPrint)
            {
                String createFineName = rcController.generatePaymentReport();
                if( createFineName == null)
                    displayErrorMessage(rcController.getErrorMessage());
                else
                    displayMessage(createFineName + " have been generated.");
            }
            else if (ae.getSource() == btnAllClockOut)
            {
                if (showYesNoDialog("", "Are you sure to meke all staff clocked out?") == DIALOG_YES)
                {
                    rcController.clockOutAll();
                    init();
                }
            }
        }
    }
    /****************************************************************
     * Input validation
    *****************************************************************/
    
    private class IntegerInputVerifier extends InputVerifier{
        private int state = 0;  //0:no range check 1:min check 2:min and max check
        private int MAX = 0;
        private int MIN = 0;
        
        public IntegerInputVerifier()
        {
            super();
        }
        
        public IntegerInputVerifier(int min)
        {
            super();
            MIN = min;
            state = 1;
        }
        
        public IntegerInputVerifier(int min, int max)
        {
            super();
            MIN = min;
            MAX = max;
            state = 2;
        }
        
        @Override public boolean verify(JComponent c)
        {
            JTextField textField = (JTextField)c;
            boolean result = false;
            
            try
            {
                int number = Integer.parseInt(textField.getText());
                
                switch(state)
                {
                    case 0:
                        result = true;
                    case 1:
                        if( number < MIN)
                        {
                            //UIManager.getLookAndFeel().provideErrorFeedback(c);
                            displayErrorMessage("Minimum input is " + MIN);
                            textField.setBackground( Color.red );
                            result = false;
                        }
                        else
                        {
                            textField.setBackground( UIManager.getColor( "TextField.background" ) );  
                            result = true;
                        }
                    break;
                    case 2:
                        if( number < MIN)
                        {
                            displayErrorMessage("Minimum input is " + MIN);
                            textField.setBackground( Color.red );
                            result = false;
                        }
                        else
                        {
                            if(number > MAX)
                            {
                                displayErrorMessage("Maximum input is " + MAX);
                                textField.setBackground( Color.red );
                                result = false;
                            }
                            else
                            {
                                textField.setBackground( UIManager.getColor( "TextField.background" ) );  
                                result = true;
                            }
                        }
                    break;
                }
            }catch(NumberFormatException e) {
                  displayErrorMessage("Only number is allowed.");
                  textField.setBackground( Color.red );
                result = false;
            }
            return result;
        }
    }
    
    private class DoubleInputVerifier extends InputVerifier{
        private int state = 0;  //0:no range check 1:min check 2:min and max check
        private double MAX = 0;
        private double MIN = 0;
        
        public DoubleInputVerifier()
        {
            super();
        }
        
        public DoubleInputVerifier(double min)
        {
            super();
            MIN = min;
            state = 1;
        }
        
        public DoubleInputVerifier(double min, double max)
        {
            super();
            MIN = min;
            MAX = max;
            state = 2;
        }
        
        @Override public boolean verify(JComponent c)
        {
            JTextField textField = (JTextField)c;
            boolean result = false;
            
            try
            {
                double number = Double.parseDouble(textField.getText());
                
                switch(state)
                {
                    case 0:
                        result = true;
                    case 1:
                        if( number < MIN)
                        {
                            //UIManager.getLookAndFeel().provideErrorFeedback(c);
                            displayErrorMessage("Minimum input is " + MIN);
                            textField.setBackground( Color.red );
                            result = false;
                        }
                        else
                        {
                            textField.setBackground( UIManager.getColor( "TextField.background" ) );  
                            result = true;
                        }
                    break;
                    case 2:
                        if( number < MIN)
                        {
                            displayErrorMessage("Minimum input is " + MIN);
                            textField.setBackground( Color.red );
                            result = false;
                        }
                        else
                        {
                            if(number > MAX)
                            {
                                displayErrorMessage("Maximum input is " + MAX);
                                textField.setBackground( Color.red );
                                result = false;
                            }
                            else
                            {
                                textField.setBackground( UIManager.getColor( "TextField.background" ) );  
                                result = true;
                            }
                        }
                    break;
                }
            }catch(NumberFormatException e) {
                  displayErrorMessage("Only number is allowed.");
                  textField.setBackground( Color.red );
                result = false;
            }
            return result;
        }
    }
}
