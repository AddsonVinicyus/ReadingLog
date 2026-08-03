package com.adx.ReadingLog.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class SupabaseStorageService {

    @Value("${supabase.url}")
    private String supabaseUrl;

    @Value("${supabase.key}")
    private String supabaseKey;

    @Value("${supabase.bucket}")
    private String bucketName;

    private final RestClient restClient = RestClient.create();

    public String uploadFile(MultipartFile file){
        try {
            String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
            String uploadUrl = supabaseUrl + "/storage/v1/object/" + bucketName + "/" + fileName;

            restClient.post()
                    .uri(uploadUrl)
                    .header("Authorization", "Bearer " + supabaseKey)
                    .header("apikey", supabaseKey)
                    .header("Content-Type", file.getContentType())
                    .body(file.getBytes())
                    .retrieve()
                    .toBodilessEntity();

            return supabaseUrl + "/storage/v1/object/public/" + bucketName + "/" + fileName;
        } catch (IOException e){
            throw new RuntimeException("Falha ao ler os bytes do arquivo para upload", e);
        } catch (Exception e){
            throw new RuntimeException("Erro ao enviar o arquivo para o Supabase: " + e.getMessage(), e);
        }
    }

    public void deleteFile(String fileName){
        try {
            String url = String.format("%s/storage/v1/object/%s/%s", supabaseUrl, bucketName, fileName);
            restClient.delete()
                    .uri(url)
                    .header("Authorization", "Bearer " + supabaseKey)
                    .retrieve()
                    .toBodilessEntity();
        } catch (Exception e){
            System.err.println("Erro ao excluir image do Supabase " + e.getMessage());
        }
    }

}
