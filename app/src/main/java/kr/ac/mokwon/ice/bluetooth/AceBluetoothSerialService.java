package kr.ac.mokwon.ice.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;

public class AceBluetoothSerialService extends BluetoothSerialService {
    public AceBluetoothSerialService(Context context, BluetoothAdapter adapter) {
        super(context, adapter);
    }

    public String getSerialInput() {
        lockReadBuffer();
        String str = sReadBuffer;
        sReadBuffer = "";
        unlockReadBuffer();
        return str;
    }

    public void print(String str) {
        if (str.isEmpty()) return;
        byte[] buf = new byte[str.length()];
        for (int i = 0; i < str.length(); i++) buf[i] = (byte) str.charAt(i);
        write(buf);
    }

    public void println(String str) {
        print(str + "\r\n");
    }

    public void print(String str, int nDelayMs) {
        if (str.isEmpty()) return;
        byte[] buf = new byte[1];
        for (int i = 0; i < str.length(); i++) {
            buf[0] = (byte) str.charAt(i);
            write(buf);
            try {
                Thread.sleep(nDelayMs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void println(String str, int nDelayMs) {
        print(str + "\r\n", nDelayMs);
    }
}