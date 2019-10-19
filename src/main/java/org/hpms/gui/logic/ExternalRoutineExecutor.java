package org.hpms.gui.logic;

import java.io.*;
import java.util.Locale;

public class ExternalRoutineExecutor {

    public enum Routine {
        XML_TO_BINARY("Xml2HFloor"),
        DAE_TO_HDAT("DaeToHDat");

        private String name;

        private Object[] ret;

        Routine(String name, Object... ret) {
            this.name = name;
            this.ret = ret;
        }

        public Object[] execute(String... params) throws Exception {

            String exe = "";
            switch (OsCheck.getOperatingSystemType()) {
                case Windows:
                    exe = ".exe";
                    break;
                case MacOS:
                    exe = ".app";
                    break;
                default:
                    exe = "";
                    break;
            }
            InputStream rountineStream = getClass().getResourceAsStream("/routines" + File.separator + name + exe);
            byte[] buffer = new byte[rountineStream.available()];
            rountineStream.read(buffer);

            File targetFile = File.createTempFile(name, exe);
            OutputStream outStream = new FileOutputStream(targetFile);
            outStream.write(buffer);

            Process process = Runtime.getRuntime().exec(targetFile.getAbsolutePath(), params);
            int res = process.waitFor();
            targetFile.deleteOnExit();
            if (res != 0) {
                throw new Exception("An error occurred running external routine " + name + exe);
            }
            return ret;
        }
    }

    private static final class OsCheck {
        /**
         * types of Operating Systems
         */
        public enum OSType {
            Windows, MacOS, Linux, Other
        }


        private static OSType detectedOS;

        public static OSType getOperatingSystemType() {
            if (detectedOS == null) {
                String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
                if ((OS.indexOf("mac") >= 0) || (OS.indexOf("darwin") >= 0)) {
                    detectedOS = OSType.MacOS;
                } else if (OS.indexOf("win") >= 0) {
                    detectedOS = OSType.Windows;
                } else if (OS.indexOf("nux") >= 0) {
                    detectedOS = OSType.Linux;
                } else {
                    detectedOS = OSType.Other;
                }
            }
            return detectedOS;
        }
    }

}
