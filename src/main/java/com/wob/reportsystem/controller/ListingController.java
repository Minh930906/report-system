package com.wob.reportsystem.controller;

import com.wob.reportsystem.dto.ReportDTO;
import com.wob.reportsystem.service.FileUploadService;
import com.wob.reportsystem.service.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ListingController {

    private final ListingService listingService;

    private final FileUploadService fileUploadService;

    @Autowired
    public ListingController(ListingService listingService, FileUploadService fileUploadService) {
        this.listingService = listingService;
        this.fileUploadService = fileUploadService;
    }

    @Value("${ftp.server}")
    private String ftpServer;

    @Value("${ftp.username}")
    private String ftpUsername;

    @Value("${ftp.password}")
    private String ftpPassword;

    @Value("${ftp.remoteFilePathReportJson}")
    private String remoteFilePathReportJson;

    @Value("${ftp.remoteFilePathImportLog}")
    private String remoteFilePathImportLog;

    @GetMapping("/getReports")
    public ResponseEntity<ReportDTO> getReports() {
        return ResponseEntity.ok().body(listingService.getReports());

    }

    @PostMapping("/upload")
    public ResponseEntity<Void> UploadFilesToFtp() {
        fileUploadService.uploadFileToFTP(ftpServer, ftpUsername, ftpPassword, "/Users/miki/Desktop/report-system/report.json", remoteFilePathReportJson);
        fileUploadService.uploadFileToFTP(ftpServer, ftpUsername, ftpPassword, "/Users/miki/Desktop/report-system/importLog.csv", remoteFilePathImportLog);
        System.out.println("The files have been successfully uploaded.");
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
