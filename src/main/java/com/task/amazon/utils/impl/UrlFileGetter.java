package com.task.amazon.utils.impl;

import com.task.amazon.exceptions.DataProcessingException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

import org.springframework.stereotype.Service;

@Service
public class UrlFileGetter {
    public void getFileFromUrl(String url, String pathForDownloadedFile) {
        try {
            ReadableByteChannel readableByteChannel = Channels
                    .newChannel(new URL(url).openStream());
            FileOutputStream fileOutputStream = null;
            fileOutputStream = new FileOutputStream(pathForDownloadedFile);
            FileChannel fileChannel = fileOutputStream.getChannel();
            fileOutputStream.getChannel()
                    .transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
        } catch (IOException e) {
            throw new DataProcessingException("Can't download file!", e);
        }
    }
}
