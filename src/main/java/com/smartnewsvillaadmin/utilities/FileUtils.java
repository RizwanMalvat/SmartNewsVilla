/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartnewsvillaadmin.utilities;

import com.smartnewsvillaadmin.entities.AdminAuth;
import java.util.UUID;

/**
 *
 * @author 1003
 */
public class FileUtils {

    public String rename(String moduleName, String filename, AdminAuth Accounts) {

        synchronized (FileUtils.class) {
            try {
                FolderUtil folderUtil = new FolderUtil();
                folderUtil.creatFolder(Accounts);
                folderUtil = null;
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (filename == null || filename.equals("")) {
                return null;
            }
            String fileExtension = filename.substring(filename.lastIndexOf("."), filename.length());
            filename = (moduleName + "-" + Accounts.getUsername() + "-" + UUID.randomUUID().toString().concat(fileExtension));
        }

        return filename;

    }

}
