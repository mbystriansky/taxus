package sk.tnet.rest.converters;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
//import org.springframework.http.HttpOutputMessage;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;

@Component
public class ImageHttpMessageConverter implements HttpMessageConverter<Image> {

    private List<MediaType> mediaTypes;

    public ImageHttpMessageConverter() {
        String[] readerMIMETypes = ImageIO.getReaderMIMETypes();
        mediaTypes = new ArrayList<MediaType>(readerMIMETypes.length);
        for (String string : readerMIMETypes) {
            mediaTypes.add(MediaType.parseMediaType(string));
        }
    }

    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return Image.class.isAssignableFrom(clazz) && (mediaType == null || mediaTypes.contains(mediaType));
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return false;
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return mediaTypes;
    }

    @Override
    public Image read(Class<? extends Image> clazz, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        return ImageIO.read(inputMessage.getBody());
    }

    @Override
    public void write(Image t, MediaType contentType, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        throw new UnsupportedOperationException("Not implemented");
    }

}