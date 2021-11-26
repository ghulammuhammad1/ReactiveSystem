package org.acme.getting.started;

import java.io.FileReader;

import io.netty.handler.codec.http.HttpContentEncoder.Result;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.core.buffer.Buffer;

public class Streaming {
    String filename;

    public Streaming() {

    }

    public Streaming(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void imperative() {
        int ch;
        String str="";
        try {

            FileReader fr = null;
            fr = new FileReader(this.filename);
            while ((ch = fr.read()) != -1)
                System.out.print((char) ch);
                // str=str+(char)ch;    
            
            fr.close();
            // return str;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            // return null;
        }

    }

    public void reactive() {
        // Uni<FileReader> uni = Uni.createFrom().item
        //vertx.fileSystem().readFile("my-file.txt");
        //uni.subscribe().with(it -> System.out.println("File content is: " + it));
        // Uni.createFrom().item("Hello")
        // .onItem().transform(item->item+" World")
        // .onItem().transform(String::toUpperCase)
        // .subscribe().with(item->System.out.println(item));
        try{
        var vertx = Vertx.vertx();
        Uni<Buffer> uni = vertx.fileSystem().readFile(this.filename);
            uni.subscribe().with(result -> System.out.println("File content is: " + result));
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
