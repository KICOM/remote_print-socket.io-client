package com.kicomlab.remote_print;

import javax.print.PrintService;
import javax.swing.*;

/**
 * Created by KICOM on 2014-12-06.
 */
public class Settings {
    public static PrintService printer = null;
    public static DefaultListModel<PrintService> printer_list = new DefaultListModel<PrintService>();
}
