package co.kr.umbbalook.vo;

/**
 * Created by whydda on 2017-08-01.
 */
public class FileVO {
    private int image_no;
    private String real_name;
    private String file_name;
    private long file_size;
    private String file_path;

    public int getImage_no() {
        return image_no;
    }

    public void setImage_no(int image_no) {
        this.image_no = image_no;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public long getFile_size() {
        return file_size;
    }

    public void setFile_size(long file_size) {
        this.file_size = file_size;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }
}
