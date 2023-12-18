package com.example.kettleintergrated;

import cn.hutool.core.io.FileUtil;
import org.pentaho.di.core.Const;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.core.plugins.PluginFolder;
import org.pentaho.di.core.plugins.StepPluginType;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;

import java.io.File;

public class KettleIntegrationExample {
    public static void main(String[] args) {
        try {
            File jndiFile = FileUtil.file("simple-jndi");
            String jndiFilePath = jndiFile.getPath();
            Const.JNDI_DIRECTORY = jndiFilePath;
            StepPluginType.getInstance().getPluginFolders().
                    add(new PluginFolder("D:/kettle-pdi/data-integration/plugins/kettle-json-plugin",false
                            , true));
            KettleEnvironment.init();
            File ktrFile = FileUtil.file("ktrs/system_hospital_cloud.ktr");
            TransMeta transMeta = new TransMeta(ktrFile.getAbsolutePath());
            Trans trans = new Trans(transMeta);
            trans.execute(null);
            trans.waitUntilFinished();

            // 打印转换日志
            // System.out.println(trans.getLogChannel().getLogText());
        } catch (KettleException e) {
            e.printStackTrace();
        }
    }
}