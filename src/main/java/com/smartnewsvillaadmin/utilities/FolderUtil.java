/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartnewsvillaadmin.utilities;

import com.smartnewsvillaadmin.entities.AdminAuth;
import com.smartnewsvillaadmin.utilities.Constant;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FolderUtil {
//
    public String getPath(AdminAuth lmAccounts) {
         
            return (Constant.FILEUPLOADPATH + Constant.PARENTFOLDERNAME + "/" + Constant.USERSFOLDERNAME + "/" + getNamePolicy(lmAccounts) + "/");
      
    }
    
    public String getPublicProfilePath() {
            return (Constant.FILEUPLOADPATH + Constant.PARENTFOLDERNAME + "/" + Constant.PUBLICFOLDERERNAME + "/");
    }
//    
//    /**
//     *
//     * @param lmAccounts
//     */
    public void creatFolder(AdminAuth Accounts) {
        synchronized (FolderUtil.class) {
            try {
                // creating parrent folder
                if (!isFolderExist(Constant.FILEUPLOADPATH + Constant.PARENTFOLDERNAME)) {
                    create(Constant.FILEUPLOADPATH + Constant.PARENTFOLDERNAME);
                }
                // creating Admin folder
                if (!isFolderExist(Constant.FILEUPLOADPATH + Constant.PARENTFOLDERNAME + "/" + Constant.ADMINFOLDERNAME)) {
                    create(Constant.FILEUPLOADPATH + Constant.PARENTFOLDERNAME + "/" + Constant.ADMINFOLDERNAME);
                }
                // creating users folder
                if (!isFolderExist((Constant.FILEUPLOADPATH + Constant.PARENTFOLDERNAME + "/" + Constant.USERSFOLDERNAME))) {
                    create((Constant.FILEUPLOADPATH + Constant.PARENTFOLDERNAME + "/" + Constant.USERSFOLDERNAME));
                }
                // creating users folder
                if (!isFolderExist((Constant.FILEUPLOADPATH + Constant.PARENTFOLDERNAME + "/" + Constant.USERSFOLDERNAME))) {
                    create(Constant.FILEUPLOADPATH + Constant.PARENTFOLDERNAME + "/" + Constant.USERSFOLDERNAME);
                }
                // creating user sub  folders
                if (!isFolderExist((Constant.FILEUPLOADPATH + Constant.PARENTFOLDERNAME + "/" + Constant.USERSFOLDERNAME + "/" + getNamePolicy(Accounts)))) {
                    create(Constant.FILEUPLOADPATH + Constant.PARENTFOLDERNAME + "/" + Constant.USERSFOLDERNAME + "/" + getNamePolicy(Accounts));
                }
                // creating user Artist Profile Public folders
                if (!isFolderExist((Constant.FILEUPLOADPATH + Constant.PARENTFOLDERNAME + "/" + Constant.PUBLICFOLDERERNAME))) {
                    create(Constant.FILEUPLOADPATH + Constant.PARENTFOLDERNAME + "/" + Constant.PUBLICFOLDERERNAME);
                }
                
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Folder creation error =====> Account id = " + Accounts.getAuthid());
            }
        }

    }

    private boolean isFolderExist(String fullPath) {
        File file = new File(fullPath);
        return file.exists();
    }

    private boolean create(String fullPath) {
        File file = new File(fullPath);
        file.setExecutable(true, false);
        file.setReadable(true, false);
        file.setWritable(true, false);
        return file.mkdir();
    }

    private String getNamePolicy(AdminAuth Accounts) {
        return (Accounts.getUsername()+ "_" + Accounts.getAuthid()+ "_" + Accounts.getType());
    }

    public void zipDir(String zipFileName, String dir) {
        try {
            File dirObj = new File(dir);
            try (ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName))) {
                addDir(dirObj, out);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addDir(File dirObj, ZipOutputStream out) throws IOException {
        File[] files = dirObj.listFiles();
        byte[] tmpBuf = new byte[1024];

        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                addDir(files[i], out);
                continue;
            }
            FileInputStream in = new FileInputStream(files[i].getAbsolutePath());
            out.putNextEntry(new ZipEntry(files[i].getAbsolutePath()));
            int len;
            while ((len = in.read(tmpBuf)) > 0) {
                out.write(tmpBuf, 0, len);
            }
            out.closeEntry();
            in.close();
        }
    }
}
