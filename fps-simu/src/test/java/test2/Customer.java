package test2;


import javax.persistence.*;

@Entity//entity将这个表作为hibernate的实体类
@Table(name = "cst_ customer")//用来映射的表名
public class Customer {

    /**
     * @Id: 声明主键的配置
     * @GeneratedValue: 配置主键的生成策略
    strategy
    Generat ionType . IDENTITY :自增，mysql
     *底层数据库必须支持自动增长(底层数据库支持的自动增长方式，对id自增)
     *
    GenerationType . SEQUENCE :序列，oracle
     *
     *底层数据库必须支持序列
    GenerationType. TABLE : jpa提供的一 种机制，通过- - 张数据库表的形式帮助我们完成主键自增
     *
    GenerationType .AUTO :由程 序自动的帮助我们选择主键生成策略
     * @Column: 配置属性和字段的映射关系
    name:数据库表中字段的名称
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private Long custId; //客户 的主键

    @Column(name = "cust_name" )
    private String custName;//客户 名称
    @Column(name= "cust_address" )
    private String custAddress;//客户 地址

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }
}
