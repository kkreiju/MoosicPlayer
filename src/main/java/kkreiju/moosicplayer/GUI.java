package kkreiju.moosicplayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.*;
import java.util.ArrayList;

/**
 *
 * @author Arjay
 */
public class GUI extends javax.swing.JFrame implements ActionListener {

    Songs songs = new Songs();
    Timer timer = new Timer(1000, this);
    int i = 0;
    int minute = 0;
    int second = 0;
    int tempVolume;
    boolean onLoop = false;

    //uninitialized music art
    ImageIcon noMusicArt = new ImageIcon("src\\main\\java\\kkreiju\\moosicplayer\\textures\\icon.png");
    Image scalednoMusicArt = noMusicArt.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
    Image logo = noMusicArt.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);

    //TEXTURES
    ImageIcon noplayButton = new ImageIcon("src\\main\\java\\kkreiju\\moosicplayer\\textures\\play.png");
    Image scaledPlayButton = noplayButton.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
    ImageIcon nopauseButton = new ImageIcon("src\\main\\java\\kkreiju\\moosicplayer\\textures\\pause.png");
    Image scaledPauseButton = nopauseButton.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
    ImageIcon noloopinactiveButton = new ImageIcon("src\\main\\java\\kkreiju\\moosicplayer\\textures\\notOnLoop.png");
    Image scaledLoopInactiveButton = noloopinactiveButton.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
    ImageIcon noloopactiveButton = new ImageIcon("src\\main\\java\\kkreiju\\moosicplayer\\textures\\onLoop.png");
    Image scaledLoopActiveButton = noloopactiveButton.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
    ImageIcon nopreviousButton = new ImageIcon("src\\main\\java\\kkreiju\\moosicplayer\\textures\\previous.png");
    Image scaledPreviousButton = nopreviousButton.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
    ImageIcon nonextButton = new ImageIcon("src\\main\\java\\kkreiju\\moosicplayer\\textures\\next.png");
    Image scaledNextButton = nonextButton.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
    ImageIcon noshuffleButton = new ImageIcon("src\\main\\java\\kkreiju\\moosicplayer\\textures\\shuffle.png");
    Image scaledShuffleButton = noshuffleButton.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
    ImageIcon novolumeHigh = new ImageIcon("src\\main\\java\\kkreiju\\moosicplayer\\textures\\volumeHigh.png");
    Image scaledVolumeHigh = novolumeHigh.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
    ImageIcon novolumeMedium = new ImageIcon("src\\main\\java\\kkreiju\\moosicplayer\\textures\\volumeMedium.png");
    Image scaledVolumeMedium = novolumeMedium.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
    ImageIcon novolumeLow = new ImageIcon("src\\main\\java\\kkreiju\\moosicplayer\\textures\\volumeLow.png");
    Image scaledVolumeLow = novolumeLow.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
    ImageIcon novolumeMute = new ImageIcon("src\\main\\java\\kkreiju\\moosicplayer\\textures\\volumeMute.png");
    Image scaledVolumeMute = novolumeMute.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
    ImageIcon noPlaying = new ImageIcon("src\\main\\java\\kkreiju\\moosicplayer\\textures\\nowPlaying.gif");
    Image scalednowPlaying = noPlaying.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);

    /**
     * Creates new form GUI
     */
    public GUI() {
        ImageIcon icon = new ImageIcon("src\\main\\java\\kkreiju\\moosicplayer\\textures\\icon.png");
        setIconImage(icon.getImage());
        this.setUndecorated(true);
        //this.setContentPane(new JLabel(new ImageIcon("src\\main\\java\\kkreiju\\moosicplayer\\textures\\background.jpg")));
        initComponents();
        this.tempVolume = volume.getValue();
        musicSlider.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        volume.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                volumeStatic(evt);
                formKeyPressed(evt);
            }
        });
        songs.InitializeData();
        changeSongListData();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        musicSlider.setMaximum(songs.calculateTime);
        long microsecondValue = songs.clip.getMicrosecondPosition();
        minute = ((int) microsecondValue / 1_000_000) / 60;
        second = ((int) microsecondValue / 1_000_000) % 60;

        if (i < songs.calculateTime) {
            musicSlider.setValue(i);
        } else {
            timer.stop();
            playButton.setIcon(new javax.swing.ImageIcon(scaledPlayButton));
            songs.hasStarted = false;
            songs.clip = null;
            songs.setIsPlaying(false);
        }

        String s = "" + second;
        i++;
        if (s.length() == 1) {
            s = "0".concat(s);
        }
        startingTime.setText(minute + ":" + s);

        if (!timer.isRunning()) {
            i = 0;
            if (onLoop) {
                playMethod();
                changeSongListData();
                nowPlayingTexture();
            } else {
                if (songs.index < songs.artistName.size() - 1) {
                    songs.index++;
                    changeData();
                    playMethod();
                    changeSongListData();
                    nowPlayingTexture();
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        startingTime = new javax.swing.JLabel();
        musicSlider = new javax.swing.JSlider();
        endingTime = new javax.swing.JLabel();
        playButton = new javax.swing.JLabel();
        loopButton = new javax.swing.JLabel();
        shuffleButton = new javax.swing.JLabel();
        previousButton = new javax.swing.JLabel();
        nextImageButton = new javax.swing.JLabel();
        actionLog = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        musicTitle = new javax.swing.JLabel();
        artistName = new javax.swing.JLabel();
        coverArt = new javax.swing.JLabel();
        volume = new javax.swing.JSlider();
        volumeButton = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        scroller = new javax.swing.JScrollBar();
        songPlay1 = new javax.swing.JLabel();
        songPlay2 = new javax.swing.JLabel();
        songPlay3 = new javax.swing.JLabel();
        songPlay4 = new javax.swing.JLabel();
        songPlayTitle1 = new javax.swing.JLabel();
        songPlayTitle2 = new javax.swing.JLabel();
        songPlayTitle3 = new javax.swing.JLabel();
        songPlayTitle4 = new javax.swing.JLabel();
        songNumberTitle1 = new javax.swing.JLabel();
        songNumberTitle2 = new javax.swing.JLabel();
        songNumberTitle3 = new javax.swing.JLabel();
        songNumberTitle4 = new javax.swing.JLabel();
        songCoverArt1 = new javax.swing.JLabel();
        songCoverArt2 = new javax.swing.JLabel();
        songCoverArt3 = new javax.swing.JLabel();
        songCoverArt4 = new javax.swing.JLabel();
        songArtistTitle1 = new javax.swing.JLabel();
        songArtistTitle2 = new javax.swing.JLabel();
        songArtistTitle3 = new javax.swing.JLabel();
        songArtistTitle4 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        lyricsPane = new javax.swing.JScrollPane();
        lyricsDisplay = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1080, 560));
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setMaximumSize(new java.awt.Dimension(439, 61));
        jPanel2.setMinimumSize(new java.awt.Dimension(439, 61));

        startingTime.setForeground(new java.awt.Color(255, 255, 255));
        startingTime.setText("--:--");

        musicSlider.setBackground(new java.awt.Color(102, 102, 102));
        musicSlider.setValue(0);
        musicSlider.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        musicSlider.setEnabled(false);
        musicSlider.setMaximumSize(new java.awt.Dimension(200, 20));
        musicSlider.setMinimumSize(new java.awt.Dimension(200, 20));
        musicSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                musicSliderStateChanged(evt);
            }
        });
        musicSlider.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                musicSliderMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                musicSliderMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                musicSliderMouseReleased(evt);
            }
        });

        endingTime.setForeground(new java.awt.Color(255, 255, 255));
        endingTime.setText("--:--");

        playButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        playButton.setIcon(new javax.swing.ImageIcon(scaledPlayButton));
        playButton.setText("");
        playButton.setMaximumSize(new java.awt.Dimension(25, 25));
        playButton.setMinimumSize(new java.awt.Dimension(25, 25));
        playButton.setPreferredSize(new java.awt.Dimension(25, 25));
        playButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                playButtonMouseClicked(evt);
            }
        });

        loopButton.setIcon(new javax.swing.ImageIcon(scaledLoopInactiveButton));
        loopButton.setText("");
        loopButton.setMaximumSize(new java.awt.Dimension(25, 25));
        loopButton.setMinimumSize(new java.awt.Dimension(25, 25));
        loopButton.setPreferredSize(new java.awt.Dimension(25, 25));
        loopButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loopButtonMouseClicked(evt);
            }
        });

        shuffleButton.setIcon(new javax.swing.ImageIcon(scaledShuffleButton));
        shuffleButton.setText("");
        shuffleButton.setMaximumSize(new java.awt.Dimension(25, 25));
        shuffleButton.setMinimumSize(new java.awt.Dimension(25, 25));
        shuffleButton.setPreferredSize(new java.awt.Dimension(25, 25));
        shuffleButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                shuffleButtonMouseClicked(evt);
            }
        });

        previousButton.setIcon(new javax.swing.ImageIcon(scaledPreviousButton));
        previousButton.setText("");
        previousButton.setMaximumSize(new java.awt.Dimension(25, 25));
        previousButton.setMinimumSize(new java.awt.Dimension(25, 25));
        previousButton.setPreferredSize(new java.awt.Dimension(25, 25));
        previousButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                previousButtonMouseClicked(evt);
            }
        });

        nextImageButton.setIcon(new javax.swing.ImageIcon(scaledNextButton));
        nextImageButton.setText("");
        nextImageButton.setMaximumSize(new java.awt.Dimension(25, 25));
        nextImageButton.setMinimumSize(new java.awt.Dimension(25, 25));
        nextImageButton.setPreferredSize(new java.awt.Dimension(25, 25));
        nextImageButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nextImageButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(musicSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(startingTime)
                                .addGap(190, 190, 190)
                                .addComponent(endingTime))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(shuffleButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(previousButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(playButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(nextImageButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addComponent(loopButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(14, 14, 14)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(playButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loopButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(shuffleButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(previousButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nextImageButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startingTime)
                    .addComponent(endingTime))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(musicSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        actionLog.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        actionLog.setForeground(new java.awt.Color(255, 255, 255));
        actionLog.setText("Action Log:");

        jPanel4.setBackground(new java.awt.Color(102, 102, 102));

        musicTitle.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        musicTitle.setForeground(new java.awt.Color(255, 255, 255));
        musicTitle.setText("Music Title");

        artistName.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        artistName.setForeground(new java.awt.Color(255, 255, 255));
        artistName.setText("Artist Name");

        coverArt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        coverArt.setIcon(new javax.swing.ImageIcon(scalednoMusicArt));
        coverArt.setText("");
        coverArt.setMaximumSize(new java.awt.Dimension(120, 120));
        coverArt.setMinimumSize(new java.awt.Dimension(120, 120));
        coverArt.setPreferredSize(new java.awt.Dimension(120, 120));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(coverArt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(17, 17, 17)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(musicTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(artistName, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(musicTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(artistName)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(coverArt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        volume.setBackground(new java.awt.Color(102, 102, 102));
        volume.setValue(81);
        volume.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                volumeStateChanged(evt);
            }
        });
        volume.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                volumeMouseReleased(evt);
            }
        });

        volumeButton.setIcon(new javax.swing.ImageIcon(scaledVolumeHigh));
        volumeButton.setText("");
        volumeButton.setMaximumSize(new java.awt.Dimension(20, 20));
        volumeButton.setMinimumSize(new java.awt.Dimension(20, 20));
        volumeButton.setPreferredSize(new java.awt.Dimension(20, 20));
        volumeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                volumeButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 232, Short.MAX_VALUE)
                .addComponent(volumeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(actionLog, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(volume, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(volume, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(actionLog))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(volumeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jPanel3MouseWheelMoved(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Songs");

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator4)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        scroller.setBlockIncrement(1);
        scroller.setMaximum(9);
        scroller.setMinimum(1);
        scroller.setVisibleAmount(2);
        scroller.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                scrollerMouseWheelMoved(evt);
            }
        });
        scroller.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
            public void adjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {
                scrollerAdjustmentValueChanged(evt);
            }
        });

        songPlay1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        songPlay1.setIcon(new javax.swing.ImageIcon(scaledPlayButton));
        songPlay1.setText("");
        songPlay1.setMaximumSize(new java.awt.Dimension(25, 25));
        songPlay1.setMinimumSize(new java.awt.Dimension(25, 25));
        songPlay1.setPreferredSize(new java.awt.Dimension(25, 25));
        songPlay1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                songPlay1MouseClicked(evt);
            }
        });

        songPlay2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        songPlay2.setIcon(new javax.swing.ImageIcon(scaledPlayButton));
        songPlay2.setText("");
        songPlay2.setMaximumSize(new java.awt.Dimension(25, 25));
        songPlay2.setMinimumSize(new java.awt.Dimension(25, 25));
        songPlay2.setPreferredSize(new java.awt.Dimension(25, 25));
        songPlay2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                songPlay2MouseClicked(evt);
            }
        });

        songPlay3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        songPlay3.setIcon(new javax.swing.ImageIcon(scaledPlayButton));
        songPlay3.setText("");
        songPlay3.setMaximumSize(new java.awt.Dimension(25, 25));
        songPlay3.setMinimumSize(new java.awt.Dimension(25, 25));
        songPlay3.setPreferredSize(new java.awt.Dimension(25, 25));
        songPlay3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                songPlay3MouseClicked(evt);
            }
        });
        songPlay3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                songPlay3KeyPressed(evt);
            }
        });

        songPlay4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        songPlay4.setIcon(new javax.swing.ImageIcon(scaledPlayButton));
        songPlay4.setText("");
        songPlay4.setMaximumSize(new java.awt.Dimension(25, 25));
        songPlay4.setMinimumSize(new java.awt.Dimension(25, 25));
        songPlay4.setPreferredSize(new java.awt.Dimension(25, 25));
        songPlay4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                songPlay4MouseClicked(evt);
            }
        });

        songPlayTitle1.setText("");

        songPlayTitle2.setText("");

        songPlayTitle3.setText("");

        songPlayTitle4.setText("");

        songNumberTitle1.setText("");

        songNumberTitle2.setText("");

        songNumberTitle3.setText("");

        songNumberTitle4.setText("");

        songCoverArt1.setText("");
        songCoverArt1.setMaximumSize(new java.awt.Dimension(75, 75));
        songCoverArt1.setMinimumSize(new java.awt.Dimension(75, 75));
        songCoverArt1.setPreferredSize(new java.awt.Dimension(75, 75));

        songCoverArt2.setText("");
        songCoverArt2.setMaximumSize(new java.awt.Dimension(75, 75));
        songCoverArt2.setMinimumSize(new java.awt.Dimension(75, 75));
        songCoverArt2.setPreferredSize(new java.awt.Dimension(75, 75));

        songCoverArt3.setText("");
        songCoverArt3.setMaximumSize(new java.awt.Dimension(75, 75));
        songCoverArt3.setMinimumSize(new java.awt.Dimension(75, 75));
        songCoverArt3.setPreferredSize(new java.awt.Dimension(75, 75));

        songCoverArt4.setText("");
        songCoverArt4.setMaximumSize(new java.awt.Dimension(75, 75));
        songCoverArt4.setMinimumSize(new java.awt.Dimension(75, 75));
        songCoverArt4.setPreferredSize(new java.awt.Dimension(75, 75));

        songArtistTitle1.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        songArtistTitle1.setText("");

        songArtistTitle2.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        songArtistTitle2.setText("");

        songArtistTitle3.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        songArtistTitle3.setText("");

        songArtistTitle4.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        songArtistTitle4.setText("");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator3)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(songPlay2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(songNumberTitle2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(songCoverArt2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(songArtistTitle2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(songPlayTitle2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(songPlay3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(songNumberTitle3, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(songCoverArt3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(songArtistTitle3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(songPlayTitle3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(songPlay1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(songNumberTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(songCoverArt1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(songArtistTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(songPlayTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(songPlay4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(songNumberTitle4, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(songCoverArt4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(songPlayTitle4, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                                            .addComponent(songArtistTitle4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(scroller, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(songNumberTitle1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(songPlay1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                            .addGap(14, 14, 14)
                                            .addComponent(songPlayTitle1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(songArtistTitle1)))
                                    .addComponent(songCoverArt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(songNumberTitle2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(songPlay2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(songPlayTitle2)
                                .addGap(12, 12, 12)
                                .addComponent(songArtistTitle2)
                                .addGap(17, 17, 17))
                            .addComponent(songCoverArt2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(songPlay3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(songNumberTitle3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(songCoverArt3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(songPlayTitle3)
                                        .addGap(12, 12, 12)
                                        .addComponent(songArtistTitle3)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(songPlay4, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(songNumberTitle4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(songCoverArt4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(songPlayTitle4)
                                .addGap(12, 12, 12)
                                .addComponent(songArtistTitle4)))
                        .addGap(10, 10, 10))
                    .addComponent(scroller, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel7.setMaximumSize(new java.awt.Dimension(356, 426));
        jPanel7.setMinimumSize(new java.awt.Dimension(356, 426));
        jPanel7.setPreferredSize(new java.awt.Dimension(356, 426));
        jPanel7.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jPanel7MouseWheelMoved(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Lyrics");

        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));

        lyricsPane.setBorder(null);
        lyricsPane.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                lyricsPaneMouseWheelMoved(evt);
            }
        });

        lyricsDisplay.setFont(new java.awt.Font("Gill Sans MT", 0, 12)); // NOI18N
        lyricsDisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lyricsDisplay.setText("");
        lyricsPane.setViewportView(lyricsDisplay);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator5)
                    .addComponent(lyricsPane)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lyricsPane, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE))
        );

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(logo));
        jLabel3.setText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addComponent(jLabel3))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void playButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playButtonMouseClicked
        changeData();
        playMethod();
        endingTime.setText(songs.displayEndTime);
    }//GEN-LAST:event_playButtonMouseClicked

    private void loopButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loopButtonMouseClicked
        loopButton();
    }//GEN-LAST:event_loopButtonMouseClicked

    private void shuffleButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shuffleButtonMouseClicked
        shuffleButton();
    }//GEN-LAST:event_shuffleButtonMouseClicked

    private void previousButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_previousButtonMouseClicked
        previousButton();
    }//GEN-LAST:event_previousButtonMouseClicked

    private void nextImageButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextImageButtonMouseClicked
        nextButton();
    }//GEN-LAST:event_nextImageButtonMouseClicked

    private void musicSliderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_musicSliderMousePressed
        if (timer.isRunning()) {
            timer.stop();
        }
    }//GEN-LAST:event_musicSliderMousePressed

    private void musicSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_musicSliderStateChanged
        minute = musicSlider.getValue() / 60;
        second = musicSlider.getValue() % 60;
        String s = "" + second;
        if (s.length() == 1) {
            s = "0".concat(s);
        }
        startingTime.setText(minute + ":" + s);
    }//GEN-LAST:event_musicSliderStateChanged

    private void musicSliderMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_musicSliderMouseReleased
        if (!timer.isRunning() && songs.getIsPlaying()) {
            timer.start();
        }
        i = musicSlider.getValue();
        minute = musicSlider.getValue() / 60;
        second = musicSlider.getValue() % 60;
        String s = "" + second;
        if (s.length() == 1) {
            s = "0".concat(s);
        }
        startingTime.setText(minute + ":" + s);
        songs.changeTime = i * 1_000_000;
        songs.hasStarted = true;
        if (songs.getIsPlaying()) {
            songs.ChangeValue();
        }
    }//GEN-LAST:event_musicSliderMouseReleased

    private void musicSliderMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_musicSliderMouseExited

    }//GEN-LAST:event_musicSliderMouseExited

    private void scrollerAdjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {//GEN-FIRST:event_scrollerAdjustmentValueChanged
        changeSongListData();
        nowPlayingTexture();
    }//GEN-LAST:event_scrollerAdjustmentValueChanged

    private void songPlay1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_songPlay1MouseClicked
        if(songs.index != scroller.getValue() - 1){
            songs.index = scroller.getValue() - 1;
            songListPlay();
            changeSongListData();
            nowPlayingTexture();
        }
        if(songs.clip == null){
            songs.index = scroller.getValue() - 1;
            songListPlay();
            changeSongListData();
            nowPlayingTexture();
        }
    }//GEN-LAST:event_songPlay1MouseClicked

    private void songPlay2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_songPlay2MouseClicked
        if(songs.index != scroller.getValue()){
            songs.index = scroller.getValue();
            songListPlay();
            changeSongListData();
            nowPlayingTexture();
        }
    }//GEN-LAST:event_songPlay2MouseClicked

    private void songPlay3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_songPlay3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_songPlay3KeyPressed

    private void songPlay3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_songPlay3MouseClicked
        if(songs.index != scroller.getValue() + 1){
            songs.index = scroller.getValue() + 1;
            songListPlay();
            changeSongListData();
            nowPlayingTexture();
        }
    }//GEN-LAST:event_songPlay3MouseClicked

    private void songPlay4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_songPlay4MouseClicked
        if(songs.index != scroller.getValue() + 2){
            songs.index = scroller.getValue() + 2;
            songListPlay();
            changeSongListData();
            nowPlayingTexture();
        }
    }//GEN-LAST:event_songPlay4MouseClicked

    private void volumeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_volumeStateChanged
        volumeMethod();
    }//GEN-LAST:event_volumeStateChanged

    private void volumeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volumeButtonMouseClicked
        mute();
    }//GEN-LAST:event_volumeButtonMouseClicked

    private void scrollerMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_scrollerMouseWheelMoved
        int turn = evt.getWheelRotation();
        if(turn < 0){
            scroller.setValue(scroller.getValue() - 1);
        }
        else{
            scroller.setValue(scroller.getValue() + 1);
        }
    }//GEN-LAST:event_scrollerMouseWheelMoved

    private void jPanel3MouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jPanel3MouseWheelMoved
        int turn = evt.getWheelRotation();
        if(turn < 0){
            scroller.setValue(scroller.getValue() - 1);
        }
        else{
            scroller.setValue(scroller.getValue() + 1);
        }
    }//GEN-LAST:event_jPanel3MouseWheelMoved

    private void volumeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_volumeMouseReleased
        tempVolume = volume.getValue();
    }//GEN-LAST:event_volumeMouseReleased

    private void jPanel7MouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jPanel7MouseWheelMoved
        int turn = evt.getWheelRotation();
        if(turn < 0){
            lyricsPane.getVerticalScrollBar().setValue(lyricsPane.getVerticalScrollBar().getValue() - 15);
        }
        else{
            lyricsPane.getVerticalScrollBar().setValue(lyricsPane.getVerticalScrollBar().getValue() + 15);
        }
    }//GEN-LAST:event_jPanel7MouseWheelMoved

    private void lyricsPaneMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_lyricsPaneMouseWheelMoved
        int turn = evt.getWheelRotation();
        if(turn < 0){
            lyricsPane.getVerticalScrollBar().setValue(lyricsPane.getVerticalScrollBar().getValue() - 15);
        }
        else{
            lyricsPane.getVerticalScrollBar().setValue(lyricsPane.getVerticalScrollBar().getValue() + 15);
        }
    }//GEN-LAST:event_lyricsPaneMouseWheelMoved

    // I DELETED THE INITCOMPONENTS FUNCTIONS NOW WHAT HAVE I DONE HUHU ANYWAY AYAW HILABTI!
    private void formKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            changeData();
            playMethod();
            endingTime.setText(songs.displayEndTime);
        } else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            nextButton();
        } else if (evt.getKeyCode() == KeyEvent.VK_LEFT) {
            previousButton();
        } else if (evt.getKeyCode() == KeyEvent.VK_L) {
            loopButton();
        } else if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        } else if (evt.getKeyCode() == KeyEvent.VK_S) {
            shuffleButton();
        } else if (evt.getKeyCode() == KeyEvent.VK_M) {
            mute();
        }
    }
    
    private void volumeStatic(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_RIGHT || evt.getKeyCode() == KeyEvent.VK_LEFT || volume.getValue() != tempVolume) {
            volume.setValue(tempVolume);
        }
    }

    public void prevOrNext() {
        timer.stop();
        if (songs.clip != null) {
            songs.clip.close();
        }
        songs.setIsPlaying(false);
        songs.hasStarted = false;
        songs.clip = null;
        i = 0;
        minute = 0;
        second = 0;
        musicSlider.setValue(0);
        startingTime.setText("0:00");
        changeData();
        changeSongListData();
        nowPlayingTexture();
        songs.Play(songs.songTitle.get(songs.index), songs.artistName.get(songs.index));
        timer.start();
        volumeMethod();
        endingTime.setText(songs.displayEndTime);
    }

    public void changeData() {
        if (!songs.getIsPlaying()) {
            songs.InitializeData();
        }

        musicTitle.setText(songs.songTitle.get(songs.index));
        artistName.setText(songs.artistName.get(songs.index));

        //COVER ART
        ImageIcon ca = new ImageIcon("src\\main\\java\\kkreiju\\moosicplayer\\textures\\coverarts\\" + songs.getSongTitle + ".jpg");
        Image scImg = ca.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        coverArt.setIcon(new javax.swing.ImageIcon(scImg));
        
        //Lyrics
        if(songs.getSongTitle == "Always"){
            Lyrics l = new Lyrics();
            l.Always();
            lyricsDisplay.setText(l.lyrics);
        }
        else if(songs.getSongTitle == "Come Inside of My Heart"){
            Lyrics l = new Lyrics();
            l.ComeInsideOfMyHeart();
            lyricsDisplay.setText(l.lyrics);
        }
        else if(songs.getSongTitle == "Disenchanted"){
            Lyrics l = new Lyrics();
            l.Disenchanted();
            lyricsDisplay.setText(l.lyrics);
        }
        else if(songs.getSongTitle == "Enchanted"){
            Lyrics l = new Lyrics();
            l.Enchanted();
            lyricsDisplay.setText(l.lyrics);
        }
        else if(songs.getSongTitle == "Fallen"){
            Lyrics l = new Lyrics();
            l.Fallen();
            lyricsDisplay.setText(l.lyrics);
        }
        else if(songs.getSongTitle == "lowkey"){
            Lyrics l = new Lyrics();
            l.lowkey();
            lyricsDisplay.setText(l.lyrics);
        }
        else if(songs.getSongTitle == "Maybe Maybe"){
            Lyrics l = new Lyrics();
            l.MaybeMaybe();
            lyricsDisplay.setText(l.lyrics);
        }
        else if(songs.getSongTitle == "Runaway Baby"){
            Lyrics l = new Lyrics();
            l.RunawayBaby();
            lyricsDisplay.setText(l.lyrics);
        }
        else if(songs.getSongTitle == "Sanctuary"){
            Lyrics l = new Lyrics();
            l.Sanctuary();
            lyricsDisplay.setText(l.lyrics);
        }
         else if(songs.getSongTitle == "She Will Be Loved"){
            Lyrics l = new Lyrics();
            l.SheWillBeLoved();
            lyricsDisplay.setText(l.lyrics);
        }
        else{
            Lyrics l = new Lyrics();
            lyricsDisplay.setText("Lyrics not Found");
        }
    }

    public void changeSongListData() {
        songNumberTitle1.setText(scroller.getValue() + "");
        songNumberTitle2.setText((scroller.getValue() + 1) + "");
        songNumberTitle3.setText((scroller.getValue() + 2) + "");
        songNumberTitle4.setText((scroller.getValue() + 3) + "");
        songPlayTitle1.setText(songs.songTitle.get(scroller.getValue() - 1));
        songPlayTitle2.setText(songs.songTitle.get(scroller.getValue()));
        songPlayTitle3.setText(songs.songTitle.get(scroller.getValue() + 1));
        songPlayTitle4.setText(songs.songTitle.get(scroller.getValue() + 2));
        songArtistTitle1.setText(songs.artistName.get(scroller.getValue() - 1));
        songArtistTitle2.setText(songs.artistName.get(scroller.getValue()));
        songArtistTitle3.setText(songs.artistName.get(scroller.getValue() + 1));
        songArtistTitle4.setText(songs.artistName.get(scroller.getValue() + 2));

        //COVER ART
        ImageIcon caPlay1 = new ImageIcon("src\\main\\java\\kkreiju\\moosicplayer\\textures\\coverarts\\" + songs.songTitle.get(scroller.getValue() - 1) + ".jpg");
        Image scPlay1 = caPlay1.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        songCoverArt1.setIcon(new javax.swing.ImageIcon(scPlay1));
        ImageIcon caPlay2 = new ImageIcon("src\\main\\java\\kkreiju\\moosicplayer\\textures\\coverarts\\" + songs.songTitle.get(scroller.getValue()) + ".jpg");
        Image scPlay2 = caPlay2.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        songCoverArt2.setIcon(new javax.swing.ImageIcon(scPlay2));
        ImageIcon caPlay3 = new ImageIcon("src\\main\\java\\kkreiju\\moosicplayer\\textures\\coverarts\\" + songs.songTitle.get(scroller.getValue() + 1) + ".jpg");
        Image scPlay3 = caPlay3.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        songCoverArt3.setIcon(new javax.swing.ImageIcon(scPlay3));
        ImageIcon caPlay4 = new ImageIcon("src\\main\\java\\kkreiju\\moosicplayer\\textures\\coverarts\\" + songs.songTitle.get(scroller.getValue() + 2) + ".jpg");
        Image scPlay4 = caPlay4.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        songCoverArt4.setIcon(new javax.swing.ImageIcon(scPlay4)); 
    }
    
    private void nowPlayingTexture(){
        songPlay1.setIcon(new javax.swing.ImageIcon(scaledPlayButton));
        songPlay2.setIcon(new javax.swing.ImageIcon(scaledPlayButton));
        songPlay3.setIcon(new javax.swing.ImageIcon(scaledPlayButton));
        songPlay4.setIcon(new javax.swing.ImageIcon(scaledPlayButton));
        if (songs.initialSet) {
            if (songs.index == scroller.getValue() - 1) {
                songPlay1.setIcon(new javax.swing.ImageIcon(scalednowPlaying));
            } else if (songs.index == scroller.getValue()) {
                songPlay2.setIcon(new javax.swing.ImageIcon(scalednowPlaying));
            } else if (songs.index == scroller.getValue() + 1) {
                songPlay3.setIcon(new javax.swing.ImageIcon(scalednowPlaying));
            } else if (songs.index == scroller.getValue() + 2) {
                songPlay4.setIcon(new javax.swing.ImageIcon(scalednowPlaying));
            }
        }
    }

    public void playMethod() {
        if (!songs.hasStarted) {
            startingTime.setText("0:00");
            musicSlider.setValue(0);
            timer.start();
            musicSlider.setEnabled(true);
            songs.clip = null;
            songs.setIsPlaying(false);
            i = 0;
            minute = 0;
            second = 0;
        }
        songs.Play(songs.songTitle.get(songs.index), songs.artistName.get(songs.index));
        volumeMethod();
        endingTime.setText(songs.displayEndTime);
        if (songs.getIsPlaying()) {
            actionLog.setText("Action Log: Played");
            songs.changeTime = songs.clip.getMicrosecondPosition();
            playButton.setIcon(new javax.swing.ImageIcon(scaledPauseButton));
            if (songs.clip.getMicrosecondPosition() == 0 && onLoop) {
                songs.clip.setMicrosecondPosition(0);
            }

            if (!timer.isRunning()) {
                timer.start();
            }
        } else if(!songs.getIsPlaying()){
            actionLog.setText("Action Log: Paused");
            songs.changeTime = songs.clip.getMicrosecondPosition();
            playButton.setIcon(new javax.swing.ImageIcon(scaledPlayButton));
            if (timer.isRunning()) {
                timer.stop();
            }
        }
        nowPlayingTexture();
    }

    private void songListPlay() {
        songs.setIsPlaying(false);
        songs.hasStarted = false;
        if (songs.clip != null) {
            songs.clip.stop();
            songs.clip = null;
        }
        i = 0;
        changeData();
        playMethod();
        endingTime.setText(songs.displayEndTime);
    }

    private void volumeMethod() {
        if (songs.clip != null) {
            try {
                FloatControl gainControl = (FloatControl) songs.clip.getControl(FloatControl.Type.MASTER_GAIN);
                float volumeFloat = (-25.0f + (volume.getValue() / 100.0f) * (6.0205f - (-25.0f)));
                if (volume.getValue() == 0) {
                    volumeFloat = -80.f;
                }
                gainControl.setValue(volumeFloat);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(!songs.initialSet){
            volume.setValue(81);
        }
        if (volume.getValue() >= 75) {
            volumeButton.setIcon(new javax.swing.ImageIcon(scaledVolumeHigh));
        } else if (volume.getValue() >= 50) {
            volumeButton.setIcon(new javax.swing.ImageIcon(scaledVolumeMedium));
        } else if (volume.getValue() >= 1) {
            volumeButton.setIcon(new javax.swing.ImageIcon(scaledVolumeLow));
        } else {
            volumeButton.setIcon(new javax.swing.ImageIcon(scaledVolumeMute));
        }
    }

    private void nextButton() {
        if (songs.clip != null) {
            if (songs.index < songs.artistName.size() - 1) {
                songs.index++;
                actionLog.setText("Action Log: Next");
                playButton.setIcon(new javax.swing.ImageIcon(scaledPauseButton));
                prevOrNext();
            } else {
                i = songs.calculateTime;
                musicSlider.setMaximum(i);
                minute = musicSlider.getValue() / 60;
                second = musicSlider.getValue() % 60;
                musicSlider.setValue(i);
                String s = "" + second;
                if (s.length() == 1) {
                    s = "0".concat(s);
                }
                startingTime.setText(minute + ":" + s);
                timer.stop();
                if (songs.clip != null) {
                    songs.clip.close();
                }
                playButton.setIcon(new javax.swing.ImageIcon(scaledPlayButton));
                songs.hasStarted = false;
                songs.clip = null;
            }
        }
    }

    private void previousButton() {
        if (songs.index > 0) {
            songs.index--;
        }
        actionLog.setText("Action Log: Previous");
        playButton.setIcon(new javax.swing.ImageIcon(scaledPauseButton));
        prevOrNext();
    }

    private void shuffleButton() {
        actionLog.setText("Action Log: Shuffled");
        boolean isRepeated = false;
        boolean randomNumRepeated = false;
        int maxNum = 9;
        int minNum = 0;
        int limit = 0;
        int randomNum = (int) (Math.random() * (maxNum - minNum + 1)) + minNum;
        ArrayList<Integer> randomIndex = new ArrayList<Integer>();
        if (randomIndex.isEmpty()) {
            if (songs.clip == null) {
                randomIndex.add(randomNum);
            } else {
                randomIndex.add(songs.index);
                songs.index = 0;
            }
        }
        while (randomIndex.size() != 10) {
            if (!randomNumRepeated) {
                randomNum = (int) (Math.random() * (maxNum - minNum + 1)) + minNum;
                randomNumRepeated = true;
            }
            if (limit < randomIndex.size()) {
                if (randomNum == randomIndex.get(limit)) {
                    isRepeated = true;
                    limit = 9;
                } else {
                    isRepeated = false;
                }
            }
            if (!isRepeated && limit == 9) {
                randomIndex.add(randomNum);
                randomNumRepeated = false;
            }
            limit++;
            if (limit == 10) {
                limit = 0;
                randomNumRepeated = false;
            }
        }
        for (limit = 0; limit < randomIndex.size(); limit++) {
            songs.songTitle.add(songs.songTitle.get(randomIndex.get(limit)));
            songs.artistName.add(songs.artistName.get(randomIndex.get(limit)));
        }
        for (limit = 0; limit < randomIndex.size(); limit++) {
            songs.songTitle.remove(0);
            songs.artistName.remove(0);
        }
        changeSongListData();
        nowPlayingTexture();
    }

    private void loopButton() {
        if (onLoop) {
            onLoop = false;
            loopButton.setText("Loop");
            loopButton.setIcon(new javax.swing.ImageIcon(scaledLoopInactiveButton));
        } else {
            onLoop = true;
            loopButton.setText("On Repeat");
            loopButton.setIcon(new javax.swing.ImageIcon(scaledLoopActiveButton));
        }
        actionLog.setText("Action Log: Loop");
    }

    private void mute() {
        if (volume.getValue() != 0) {
            volume.setValue(0);
            actionLog.setText("Action Log: Muted");
        } else {
            if(tempVolume == 0){
                tempVolume = 100;
            }
            volume.setValue(tempVolume);
            actionLog.setText("Action Log: Unmuted");
        }
        volumeMethod();
    }

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
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel actionLog;
    private javax.swing.JLabel artistName;
    private javax.swing.JLabel coverArt;
    private javax.swing.JLabel endingTime;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel loopButton;
    private javax.swing.JLabel lyricsDisplay;
    private javax.swing.JScrollPane lyricsPane;
    public javax.swing.JSlider musicSlider;
    private javax.swing.JLabel musicTitle;
    private javax.swing.JLabel nextImageButton;
    private javax.swing.JLabel playButton;
    private javax.swing.JLabel previousButton;
    private javax.swing.JScrollBar scroller;
    private javax.swing.JLabel shuffleButton;
    private javax.swing.JLabel songArtistTitle1;
    private javax.swing.JLabel songArtistTitle2;
    private javax.swing.JLabel songArtistTitle3;
    private javax.swing.JLabel songArtistTitle4;
    private javax.swing.JLabel songCoverArt1;
    private javax.swing.JLabel songCoverArt2;
    private javax.swing.JLabel songCoverArt3;
    private javax.swing.JLabel songCoverArt4;
    private javax.swing.JLabel songNumberTitle1;
    private javax.swing.JLabel songNumberTitle2;
    private javax.swing.JLabel songNumberTitle3;
    private javax.swing.JLabel songNumberTitle4;
    private javax.swing.JLabel songPlay1;
    private javax.swing.JLabel songPlay2;
    private javax.swing.JLabel songPlay3;
    private javax.swing.JLabel songPlay4;
    private javax.swing.JLabel songPlayTitle1;
    private javax.swing.JLabel songPlayTitle2;
    private javax.swing.JLabel songPlayTitle3;
    private javax.swing.JLabel songPlayTitle4;
    private javax.swing.JLabel startingTime;
    private javax.swing.JSlider volume;
    private javax.swing.JLabel volumeButton;
    // End of variables declaration//GEN-END:variables
}
