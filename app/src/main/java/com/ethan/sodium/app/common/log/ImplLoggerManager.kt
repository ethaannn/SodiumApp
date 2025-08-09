package com.ethan.composer.common.log

import android.content.Context
import ch.qos.logback.classic.LoggerContext
import ch.qos.logback.classic.joran.JoranConfigurator
import com.ethan.sodium.app.common.log.ILoggerManager
//import io.github.uhsk.kit.toFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.slf4j.LoggerFactory
import java.util.*

internal class ImplLoggerManager(private val mContext: Context) : ILoggerManager {

    override suspend fun init() = withContext(context = Dispatchers.IO) {
        val loggerContext: LoggerContext = LoggerFactory.getILoggerFactory() as LoggerContext
        loggerContext.reset()
        loggerContext.putProperty("LOG_DIR", mContext.externalCacheDir?.path)
        val joranConfigurator = JoranConfigurator()
        joranConfigurator.context = loggerContext
        joranConfigurator.doConfigure(mContext.assets.open("configs/logback.xml"))
        return@withContext
    }


//    override fun zip(): Uri {
//        val loggerContext: LoggerContext = LoggerFactory.getILoggerFactory() as LoggerContext
//        //val todayLogFile = File(loggerContext.getProperty("LOG_DIR").toFile(), String.format(Locale.ENGLISH, "logs/%s.log", Date().format(pattern = "yyyy-MM-dd")))
//        val logFolder = loggerContext.getProperty("LOG_DIR").toFile().resolve(relative = "logs")
//        val sharedPath = File(mContext.externalCacheDir, "shared")
//        val outputFile = File(sharedPath, "logs.zip")
//        if (outputFile.exists()) {
//            outputFile.delete()
//        }
//        FileUtils.forceMkdir(outputFile.parentFile)
//
//        val zipArchiveOutputStream = ZipArchiveOutputStream(outputFile)
//        zipArchiveOutputStream.setUseZip64(Zip64Mode.AsNeeded)
//        zipArchiveOutputStream.encoding = "UTF-8"
//
//        logFolder.listFiles()?.forEach {logFile ->
//            val zipArchiveEntry = ZipArchiveEntry(logFile, logFile.name)
//            zipArchiveOutputStream.putArchiveEntry(zipArchiveEntry)
//            zipArchiveOutputStream.write(logFile.readBytes())
//            zipArchiveOutputStream.closeArchiveEntry()
//        }
//        zipArchiveOutputStream.finish()
//        zipArchiveOutputStream.close()

//        return FileProvider.getUriForFile(mContext, "${BuildConfig.APPLICATION_ID}.providers.file", outputFile)
//    }


}