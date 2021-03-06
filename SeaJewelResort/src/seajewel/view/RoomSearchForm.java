/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seajewel.view;

import seajewel.Hotelwise;
import static seajewel.Hotelwise.searchForm;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author courtney
 */
public class RoomSearchForm extends AbstractForm {

    /**
     * Creates new form RoomSearchForm
     */
    public RoomSearchForm() {
        initComponents();
        unavailableTextField.setVisible(false);
        incorrectTextField1.setVisible(true);
        incorrectTextField1.setText("                ");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        searchButton = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        roomTypeComboBox = new javax.swing.JComboBox<>();
        arrivalTextField = new javax.swing.JTextField();
        departureTextField = new javax.swing.JTextField();
        numOfGuestsSpinner = new javax.swing.JSpinner();
        unavailableTextField = new javax.swing.JTextField();
        incorrectTextField1 = new javax.swing.JTextField();
        customerLabel = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(300, 26));

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel4.setText("Room Availability Search");

        jLabel5.setText("Number of Guests: ");

        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        jLabel7.setText("Arrival Date:");

        jLabel8.setText("Departure Date:");

        jLabel9.setText("Room Type:");

        roomTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Single", "Single with Ocean View", "Double", "Family" }));

        numOfGuestsSpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, 4, 1));

        unavailableTextField.setEditable(false);
        unavailableTextField.setText("Sorry there are no rooms of that type available.");
        unavailableTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unavailableTextFieldActionPerformed(evt);
            }
        });

        incorrectTextField1.setEditable(false);
        incorrectTextField1.setText("Please enter the arrival and departure dates (e.g. 2017-04-05)");
        incorrectTextField1.setMinimumSize(new java.awt.Dimension(200, 26));
        incorrectTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                incorrectTextField1ActionPerformed(evt);
            }
        });

        customerLabel.setEditable(false);
        customerLabel.setText("jTextField1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(33, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7))
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(numOfGuestsSpinner)
                            .addComponent(arrivalTextField)
                            .addComponent(departureTextField)
                            .addComponent(roomTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(121, 121, 121)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addComponent(unavailableTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(customerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(incorrectTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                                .addGap(8, 8, 8)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(searchButton)
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(customerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(unavailableTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(incorrectTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numOfGuestsSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(arrivalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(departureTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roomTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        Calendar currentDateCal = Calendar.getInstance(); //Get the current date
        TimeZone timeZone = TimeZone.getTimeZone("Australia/Brisbane");
        currentDateCal.setTimeZone(timeZone);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); //format it as per your requirement
        String dateNow = formatter.format(currentDateCal.getTime());
        System.out.println("Now the date is :=>  " + currentDateCal.getTime());
        java.util.Date today = currentDateCal.getTime();
        Long time = today.getTime();
        Date todayMidnight = new Date(time - time % (24 * 60 * 60 * 1000));

        String roomTypeStr = (String) roomTypeComboBox.getSelectedItem();
        java.util.Date checkInDate = null, checkOutDate = null;
        try {
            checkInDate = (java.util.Date) formatter.parse(arrivalTextField.getText());
            checkOutDate = (java.util.Date) formatter.parse(departureTextField.getText());
        } catch (ParseException ex) {
            Logger.getLogger(RoomSearchForm.class.getName()).log(Level.SEVERE, null, ex);
            this.showIncorrectError("Invalid dates entered: '" + arrivalTextField.getText() + "', '" + departureTextField.getText() + "'");
            return;
        }

        if (arrivalTextField.getText().equals(departureTextField.getText())) {
            showIncorrectError("The arrival date and the departure date must be different.");
        } else if (checkOutDate.before(new java.util.Date(checkInDate.getTime() + time % (24 * 60 * 60 * 1000)))) {
            showIncorrectError("Check out will after Check in date");
            System.out.println("Wrong input");
        } else if (checkInDate.before(todayMidnight)) {
            showIncorrectError("Arrival dates should be after today's date");
            System.out.println("TOO EARLY");
        } else if (Hotelwise.isRoomTypeAvailable(roomTypeStr, checkInDate, checkOutDate)){
           // Success, Found a room for the room type;
           // Prepare next page, display Room (room type)  details (including price)
           Hotelwise.displayRoomDetails(roomTypeStr, checkInDate, checkOutDate, (Integer) numOfGuestsSpinner.getValue());

        } else { 
            showIncorrectError("Sorry there are no rooms of that type available");
            System.out.println("No available rooms");              
        }
    }//GEN-LAST:event_searchButtonActionPerformed

    private void unavailableTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unavailableTextFieldActionPerformed
    }//GEN-LAST:event_unavailableTextFieldActionPerformed

    private void incorrectTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_incorrectTextField1ActionPerformed
    }//GEN-LAST:event_incorrectTextField1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RoomSearchForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RoomSearchForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RoomSearchForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RoomSearchForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RoomSearchForm().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField arrivalTextField;
    private javax.swing.JTextField customerLabel;
    private javax.swing.JTextField departureTextField;
    private javax.swing.JTextField incorrectTextField1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSpinner numOfGuestsSpinner;
    private javax.swing.JComboBox<String> roomTypeComboBox;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField unavailableTextField;
    // End of variables declaration//GEN-END:variables


    public void showIncorrectError(String errorText) {
        incorrectTextField1.setVisible(true);
        //incorrectTextField1.setText("");
        incorrectTextField1.setText(errorText);

        this.doLayout(); // seems no effect
    }

    public void refreshUI() {
        customerLabel.setText("Logged in as " + customer.getFirstName() + " (" + customer.getEmail() + ")");
    }

}
