package com.distributed_system.term_project.chat.controller;

import com.distributed_system.term_project.chat.service.ChunkedFileUploadInFlight;
import com.distributed_system.term_project.chat.service.SaveToFileSystem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;
import java.util.WeakHashMap;

@Slf4j
@Controller
public class FileUploadController {

    Map<String, ChunkedFileUploadInFlight> sessionToFileMap = new WeakHashMap<>();

    @PostMapping(value = "/chunk-upload", produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> handleChunkedUpload(
            @RequestBody byte[] data,
            @RequestHeader("X-Upload-Session-id") String uploadSessionId,
            @RequestHeader("X-File-Name") String fileName,
            @RequestHeader("X-File-Chunk-Sequence") Integer fileChunkSequence,
            @RequestHeader("X-File-Last") boolean isLast
    ) throws IOException {
        ChunkedFileUploadInFlight inFlightUpload = sessionToFileMap.get(uploadSessionId);
        if (inFlightUpload == null) {
            inFlightUpload = new ChunkedFileUploadInFlight(fileName, uploadSessionId);
        }
        inFlightUpload.addChunk(fileChunkSequence, data);
        if (isLast) {
            SaveToFileSystem.save(fileName, "chunk-upload", inFlightUpload.getAllBytes());
            log.info("Chunked Upload Final "+fileName);
            return ResponseEntity.ok().header(
                    HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + fileName + "\"").body("{\"success\": true}");
        } else {
            return ResponseEntity.ok().header(
                    HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + fileName + "\"").body("{\"chunk-success\": true}");
        }

    }

    @PostMapping(value = "/fetch", produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> handleFileUploadFetchFileBlob(@RequestBody(required = false) byte[] data, @RequestHeader("X-File-Name") String fileName) throws IOException {
        SaveToFileSystem.save(fileName, "fetch", data);

        log.info("Uploaded " + fileName);
        return ResponseEntity.ok().header(
                HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + fileName + "\"").body("{\"success\": true}");
    }

}
