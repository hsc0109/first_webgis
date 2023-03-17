package cn.yls.metaluccs.server.pojo.entity;

public class TestCsv {
    private Integer id;

    private String name;

    private String ty;

    private String jwd;

    private Object geom;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTy() {
        return ty;
    }

    public void setTy(String ty) {
        this.ty = ty == null ? null : ty.trim();
    }

    public String getJwd() {
        return jwd;
    }

    public void setJwd(String jwd) {
        this.jwd = jwd == null ? null : jwd.trim();
    }

    public Object getGeom() {
        return geom;
    }

    public void setGeom(Object geom) {
        this.geom = geom;
    }
}