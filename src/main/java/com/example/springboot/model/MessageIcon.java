package com.example.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Lob;
import javax.persistence.MapsId;

@Entity
@Table(name = "message_icon")
public class MessageIcon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "message_id")
    private Message message;

    @Column(name = "fileName", nullable = true)
    private String fileName;

    @Column(name = "fileType", nullable = true)
    private String fileType;

    @Lob
    @Column(name = "data", nullable = true)
    private byte[] data;

    MessageIcon() {
    }

    public MessageIcon(String fileName, String fileType, byte[] data, Message message) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.message = message;
    }

    public Long getId() {
        return this.id;
    }

    public Message getMessage() {
        return this.message;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getFileType() {
        return this.fileType;
    }

    public byte[] getData() {
        return this.data;
    }

    public Long setId(Long id) {
        return this.id = id;
    }

    public Message setMessage(Message message) {
        return this.message = message;
    }

    public String setFileName(String fileName) {
        return this.fileName = fileName;
    }

    public String setFileType(String fileType) {
        return this.fileType = fileType;
    }

    public byte[] setData(byte[] data) {
        return this.data = data;
    }
}
