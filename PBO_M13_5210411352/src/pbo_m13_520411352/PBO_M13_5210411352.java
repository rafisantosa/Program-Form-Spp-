package pbo_m13_520411352;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
public class PBO_M13_5210411352 extends javax.swing.JFrame {

    public PBO_M13_5210411352(){
        initComponents();
        
        //add action command ke radio button
        rbTF.setActionCommand("Teknik Informatika");
        rbTI.setActionCommand("Teknik Industri");
        txtId.setEditable(false);
        //add radio btn ke btn group
        bgProgramStudi.add(rbTF);
        bgProgramStudi.add(rbTI); 
        initKomponenTambahan();
    }

    private DefaultTableModel model;
    private void initKomponenTambahan() {
        
        // TODO: Inisiasi model tabel Mahasiswa
       model = (DefaultTableModel) tabelMahasiswa.getModel();
       this.loadDataToTabel();
       // Sembunyikan kolom ID Mhs
        tabelMahasiswa.getColumnModel().getColumn(6).setMinWidth(0);
        tabelMahasiswa.getColumnModel().getColumn(6).setMaxWidth(0);
    }
    // TODO: READ data dari database ke tabel
    private void loadDataToTabel() {
        model.setRowCount(0);

        try {
            Connection db = KoneksiDB.getKoneksi();
            Statement st = db.createStatement();

            String sql = "SELECT * FROM data_mahasiswa";

            ResultSet rs = st.executeQuery(sql);

            while(rs.next()) {
                Object[] data = new Object[7];
                data[0] = rs.getString("nama_mhs");
                data[1] = rs.getString("nim_mhs");
                data[2] = rs.getInt("angkatan_masuk");
                data[3] = rs.getString("semester");
                data[4] = rs.getString("prodi");
                data[5] = rs.getInt("sks_mhs");
                data[6] = rs.getInt("id_mhs");

                model.addRow(data);
            }

            rs.close();
            st.close();
        }
        catch (SQLException e) {
            System.err.println("Load data eror: " + e.getMessage());
        }
    } 
    public void printAll() throws NimHarusAngkaException, NimHarusPositifException,NimHarus10Digit,SemesterHarusValid{
       StringBuilder sb = new StringBuilder();
       long nim;
       Integer Semester ;
       
       //try catch untuk menangani NIM harus angka bilangan positif dan panjangnya < 10
       try {
           nim= Long.parseLong(txtNIM.getText());
       }catch(NumberFormatException e){
           throw new NimHarusAngkaException();
       }
       if (nim < 0){
           throw new NimHarusPositifException();
       }if (txtNIM.getText().length()>10){
           throw new NimHarus10Digit();          
       }
       
       //try catch untuk menangani Semester harus valid
       try {
            Semester=Integer.parseInt(txtSemester.getText());
            if (Semester < 0){
                throw new SemesterHarusValid();
            }if (Semester > 14){
                throw new SemesterHarusValid();
            }
        } catch (NumberFormatException e) {
            String angkatan = cmbAngkatan.getSelectedItem().toString();
            switch(angkatan){
                case "2021":
                    txtSemester.setText("II");
                    break;
                case "2020":
                    txtSemester.setText("IV");
                    break;
                case "2019":
                    txtSemester.setText("VI");
                    break;
                case "2018":
                    txtSemester.setText("VII");
                    break;
            }
        }
         try {
            Connection db = KoneksiDB.getKoneksi();

            String sql = "INSERT INTO data_mahasiswa "
                    + "(nama_mhs,nim_mhs,angkatan_masuk,semester,prodi,sks_mhs) "
                    + "VALUES (?,?,?,?,?,?)";

            PreparedStatement ps = db.prepareStatement(sql);

            ps.setString(1, txtNama.getText());
            ps.setString(2, txtNIM.getText());
            ps.setString(3, cmbAngkatan.getSelectedItem().toString());
            ps.setString(4, txtSemester.getText());
            ps.setString(5, bgProgramStudi.getSelection().getActionCommand());
            ps.setString(6, txtSKS.getText());

            int rowInsert = ps.executeUpdate();
            if (rowInsert > 0) {
                JOptionPane.showMessageDialog(this, "Data berhasil disimpan", "Success", JOptionPane.INFORMATION_MESSAGE);
            }

            ps.close();
        }
        catch (SQLException e) {
            System.err.println("Insert data eror: " + e.getMessage());
        }
        finally {
            // Load tabel mahasiswa untuk melihat perubahan
            this.loadDataToTabel();
        }   
       
       sb.append("================INFO MAHASISWA ==================");
       sb.append("\nNama Mahasiswa\t:").append(txtNama.getText()); 
       sb.append("\nNIM\t:").append(txtNIM.getText()); 
       sb.append("\nAngkatan\t:").append(cmbAngkatan.getSelectedItem());
       sb.append("\nSemester\t:").append(txtSemester.getText()); 
       sb.append("\nProgram Studi\t:").append(bgProgramStudi.getSelection().getActionCommand());
       sb.append("\nJumlah SKS\t:").append(txtSKS.getText()); 
       sb.append("\n\n");
       
       taPrint.setText(sb.toString());       
    }
 

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgProgramStudi = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        txtNama = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        txtNIM = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtSemester = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        rbTF = new javax.swing.JRadioButton();
        rbTI = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtSKS = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        btnPrint = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        cmbAngkatan = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblUser = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taPrint = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelMahasiswa = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PBO_M09_5210411352");
        setMinimumSize(new java.awt.Dimension(409, 678));

        jPanel1.setBackground(new java.awt.Color(231, 240, 253));

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Gisha", 1, 18)); // NOI18N
        jLabel2.setText("Simple SPP Form");

        jSeparator2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jPanel4.setBackground(new java.awt.Color(224, 229, 230));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setPreferredSize(new java.awt.Dimension(248, 46));

        txtNama.setFont(new java.awt.Font("Arial Unicode MS", 0, 12)); // NOI18N
        txtNama.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtNama.setMinimumSize(new java.awt.Dimension(56, 20));
        txtNama.setPreferredSize(new java.awt.Dimension(56, 20));
        txtNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Euphemia", 0, 12)); // NOI18N
        jLabel5.setText("Nama Mahasiswa    :");

        jLabel4.setText("ID :");

        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(txtId)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(224, 229, 230));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtNIM.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtNIM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNIMActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Euphemia", 0, 12)); // NOI18N
        jLabel6.setText("NIM                               :");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNIM, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNIM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(224, 229, 230));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setFont(new java.awt.Font("Euphemia", 0, 12)); // NOI18N
        jLabel8.setText("Semester                    :");

        txtSemester.setText(" ");
        txtSemester.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtSemester.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSemesterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSemester, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtSemester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(224, 229, 230));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        bgProgramStudi.add(rbTF);
        rbTF.setText("Teknik Informatika");
        rbTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbTFActionPerformed(evt);
            }
        });

        bgProgramStudi.add(rbTI);
        rbTI.setText("Teknik Industri");
        rbTI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbTIActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Euphemia", 0, 12)); // NOI18N
        jLabel9.setText("Program Studi            :");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(rbTF)
                .addGap(18, 18, 18)
                .addComponent(rbTI)
                .addGap(42, 42, 42))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbTF)
                    .addComponent(rbTI)
                    .addComponent(jLabel9))
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(224, 229, 230));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setFont(new java.awt.Font("Euphemia", 0, 12)); // NOI18N
        jLabel10.setText("Jumlah SKS Diambil :");

        txtSKS.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtSKS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSKSActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Euphemia", 0, 12)); // NOI18N
        jLabel11.setText("SKS");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSKS, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtSKS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(224, 229, 230));
        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnPrint.setBackground(new java.awt.Color(153, 204, 255));
        btnPrint.setFont(new java.awt.Font("Century", 1, 11)); // NOI18N
        btnPrint.setText("Print");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        btnReset.setBackground(new java.awt.Color(255, 102, 102));
        btnReset.setFont(new java.awt.Font("Century", 1, 11)); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(153, 255, 153));
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(255, 0, 51));
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrint)
                    .addComponent(btnReset)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete))
                .addContainerGap())
        );

        jPanel10.setBackground(new java.awt.Color(224, 229, 230));
        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cmbAngkatan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2018", "2019", "2020", "2021" }));
        cmbAngkatan.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cmbAngkatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAngkatanActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Euphemia", 0, 12)); // NOI18N
        jLabel7.setText("Angkatan                     :  ");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbAngkatan, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbAngkatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Gisha", 0, 12)); // NOI18N
        jLabel3.setText("Selamat Datang ,");

        lblUser.setFont(new java.awt.Font("Gisha", 0, 12)); // NOI18N
        lblUser.setText("User");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblUser)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblUser))
                .addGap(8, 8, 8)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Gisha", 1, 14)); // NOI18N
        jLabel1.setText("Simple SPP Print");

        taPrint.setColumns(20);
        taPrint.setFont(new java.awt.Font("Franklin Gothic Demi", 0, 13)); // NOI18N
        taPrint.setRows(5);
        taPrint.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jScrollPane1.setViewportView(taPrint);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jSeparator1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jPanel11.setBackground(new java.awt.Color(204, 255, 204));

        tabelMahasiswa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Nama", "NIM", "Angkatan", "Semester", "Program Studi", "SKS", "id"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelMahasiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelMahasiswaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelMahasiswa);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNIMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNIMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNIMActionPerformed

    private void txtSemesterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSemesterActionPerformed
        
    }//GEN-LAST:event_txtSemesterActionPerformed

    private void rbTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbTFActionPerformed

    private void rbTIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbTIActionPerformed

    private void txtSKSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSKSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSKSActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        //Membuat Objek mahasiswa
        Mahasiswa mhs = new Mahasiswa(
                txtNama.getText(), 
                txtNIM.getText(),
                Integer.parseInt(cmbAngkatan.getSelectedItem().toString()),
                txtSemester.getText(),
                bgProgramStudi.getSelection().getActionCommand(), 
                Integer.parseInt(txtSKS.getText()));        
        try {
            this.printAll();
            taPrint.append(mhs.CetakSPP());
        } catch (NimHarusAngkaException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        } catch (NimHarusPositifException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            txtNIM.setText("");
        }catch(NimHarus10Digit e){
            JOptionPane.showMessageDialog(this, e.getMessage());
            txtNIM.setText("");
        }catch(SemesterHarusValid e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }  
    }//GEN-LAST:event_btnPrintActionPerformed

    private void txtNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaActionPerformed

    private void cmbAngkatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAngkatanActionPerformed
        txtSemester.setEditable(true);
        String angkatan = cmbAngkatan.getSelectedItem().toString();
        switch(angkatan){
            case "2021":
                txtSemester.setText("II");
                break;
            case "2020":
                txtSemester.setText("IV");
                break;
            case "2019":
                txtSemester.setText("VI");
                break;
            case "2018":
                txtSemester.setText("VII");
        }
    }//GEN-LAST:event_cmbAngkatanActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        //untuk mereset isian form 
        txtNama.setText("");
        txtNIM.setText("");
        cmbAngkatan.setSelectedIndex(0);
        txtSemester.setText("");
        bgProgramStudi.clearSelection();
        txtSKS.setText("");
        txtId.setText("");
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO: UPDATE data mahasiswa 
        try {
             Connection db = KoneksiDB.getKoneksi();

            String sql = "UPDATE data_mahasiswa "
                    + "SET nama_mhs=?, nim_mhs=?, angkatan_masuk=?, semester=?, prodi=? ,sks_mhs=? "
                    + "WHERE id_mhs=?;";

            PreparedStatement ps = db.prepareStatement(sql);

            ps.setString(1, txtNama.getText());
            ps.setString(2, txtNIM.getText());
            ps.setString(3, cmbAngkatan.getSelectedItem().toString());
            ps.setString(4, txtSemester.getText());
            ps.setString(5, bgProgramStudi.getSelection().getActionCommand());
            ps.setString(6, txtSKS.getText());
            ps.setString(7, txtId.getText());

            int rowInsert = ps.executeUpdate();
            if (rowInsert > 0) {
                JOptionPane.showMessageDialog(this, "Data berhasil diperbarui", "Success", JOptionPane.INFORMATION_MESSAGE);
            }

            ps.close();
        }
        catch (SQLException e) {
            System.err.println("Update data eror: " + e.getMessage());
        }
        finally {
            // Load tabel mahasiswa untuk melihat perubahan
            this.loadDataToTabel();
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO: DELETE data mahasiswa
        try {
            Connection db = KoneksiDB.getKoneksi();

            String sql = "DELETE FROM data_mahasiswa "
                    + "WHERE id_mhs=?;";

            PreparedStatement ps = db.prepareStatement(sql);

            ps.setString(1, txtId.getText());

            int rowInsert = ps.executeUpdate();
            if (rowInsert > 0) {
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus", "Success", JOptionPane.INFORMATION_MESSAGE);
            }

            ps.close();
        }
        catch (SQLException e) {
            System.err.println("Delete data eror: " + e.getMessage());
        }
        finally {
            // Load tabel mahasiswa untuk melihat perubahan
            this.loadDataToTabel();
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tabelMahasiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMahasiswaMouseClicked
        int rowIdx = tabelMahasiswa.getSelectedRow();

        // Tampilkan data ke form
        String id = tabelMahasiswa.getValueAt(rowIdx, 6).toString();
        txtId.setText(id);

        String nama = tabelMahasiswa.getValueAt(rowIdx, 0).toString();
        txtNama.setText(nama);

        String nim = tabelMahasiswa.getValueAt(rowIdx, 1).toString();
        txtNIM.setText(nim);

        String angkatan = tabelMahasiswa.getValueAt(rowIdx, 2).toString();
        switch (angkatan) {
            case "2018":
                cmbAngkatan.setSelectedIndex(0);
                txtSemester.setText("VIII");
                break;
            case "2019":
                cmbAngkatan.setSelectedIndex(1);
                txtSemester.setText("VI");
                break;
            case "2020":
                cmbAngkatan.setSelectedIndex(2);
                txtSemester.setText("IV");
                break;
            default:
                cmbAngkatan.setSelectedIndex(3);
                txtSemester.setText("II");
        }
        

        String prodi = tabelMahasiswa.getValueAt(rowIdx, 4).toString();
        switch (prodi) {
            case "Teknik Informatika":
                rbTF.setSelected(true);
                break;
            default:
                rbTI.setSelected(true);
        }

        String sks = tabelMahasiswa.getValueAt(rowIdx, 5).toString();
        txtSKS.setText(sks);
        
    
    }//GEN-LAST:event_tabelMahasiswaMouseClicked

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
       
    }//GEN-LAST:event_txtIdActionPerformed

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
            java.util.logging.Logger.getLogger(PBO_M13_5210411352.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PBO_M13_5210411352.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PBO_M13_5210411352.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PBO_M13_5210411352.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new PBO_M13_5210411352().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgProgramStudi;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbAngkatan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblUser;
    private javax.swing.JRadioButton rbTF;
    private javax.swing.JRadioButton rbTI;
    private javax.swing.JTextArea taPrint;
    private javax.swing.JTable tabelMahasiswa;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNIM;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtSKS;
    private javax.swing.JTextField txtSemester;
    // End of variables declaration//GEN-END:variables
}
