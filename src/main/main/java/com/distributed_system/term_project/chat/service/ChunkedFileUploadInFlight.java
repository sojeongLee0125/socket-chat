package com.distributed_system.term_project.chat.service;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

@Slf4j
public class ChunkedFileUploadInFlight {

    String name;
    String uploadSessionId;
    Map<Integer, byte[]> orderedChunks = Collections.synchronizedMap(new TreeMap<>());

    /**
     * @param name
     * @param uploadSession
     */
    public ChunkedFileUploadInFlight(String name, String uploadSession) {
        this.name = name;
        this.uploadSessionId = uploadSession;

        log.info("Preparing upload for " + this.name + " uploadSessionId " + uploadSessionId);
    }

    public void addChunk(Integer packetNumber, byte[] chunk) {
        this.orderedChunks.put(packetNumber, chunk);
    }

    public byte[] getAllBytes() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        this.orderedChunks.values()
                .forEach(
                        bytes ->
                        {
                            try {
                                bos.write(bytes);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                );
        return bos.toByteArray();
    }
}