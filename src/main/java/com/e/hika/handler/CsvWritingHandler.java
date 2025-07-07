package com.e.hika.handler;

import com.e.hika.pojo.Student;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

import java.io.BufferedWriter;
import java.io.IOException;


//@Component
public class CsvWritingHandler implements ResultHandler<Student>,AutoCloseable {

    private final BufferedWriter writer;


    public CsvWritingHandler(BufferedWriter writer) throws IOException {
//        this.writer= Files.newBufferedWriter(output, StandardCharsets.UTF_8)
//        ;
           this.writer = writer;
          writer.write("id,name ,s_create_time,s_update_time");
          writer.newLine();


    }


    @Override
    public void handleResult(ResultContext<? extends Student> ctx) {

        Student s = ctx.getResultObject();
        try{
            writer.write(s.getId()+","+s.getName()+","+s.getsCreateTime()+","+s.getsUpdateTime());

            writer.newLine();

            if(ctx.getResultCount()%10_000==0){
                writer.flush();
            }

        }catch (Exception e){
            e.printStackTrace();
        }


    }


    public void close() throws IOException {
        writer.flush();
        writer.close();
    }


















    }
