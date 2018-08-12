package Controllers;

import Common.DateHelper;
import btpEntity.Employee;
import org.junit.Test;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamApiController {

    public static void main(String[] args) {

    }
    /**
     * 获取数据源
     */
    public synchronized List<Employee> GetList() {

        List<Employee> employeelist = Arrays.asList(
                new Employee(99, "李四", 59, 6666.66, DateHelper.parseString("2018-07-26 15:33:38")),
                new Employee(100, "张三", 18, 9999.99, DateHelper.parseString("2018-07-26 15:34:38")),
                new Employee(101, "王五", 28, 3333.33, DateHelper.parseString("2018-07-26 15:35:38")),
                new Employee(102, "赵六", 8, 7777.77, DateHelper.parseString("2018-07-26 15:36:38")),
                new Employee(103, "赵六", 8, 7777.77, DateHelper.parseString("2018-07-26 15:37:38")),
                new Employee(104, "赵六", 8, 7777.77, DateHelper.parseString("2018-07-26 15:38:38")),
                new Employee(105, "田七", 38, 5555.55, DateHelper.parseString("2018-07-26 15:39:38")),
                new Employee(106, "李四", 59, 6666.66, DateHelper.parseString("2018-07-26 15:40:38")),
                new Employee(107, "张三", 18, 9999.99, DateHelper.parseString("2018-07-26 15:41:38")),
                new Employee(108, "王五", 28, 3333.33, DateHelper.parseString("2018-07-26 15:42:38")),
                new Employee(109, "赵六", 8, 7777.77, DateHelper.parseString("2018-07-26 15:43:38")),
                new Employee(110, "赵六", 8, 7777.77, DateHelper.parseString("2018-07-26 15:44:38")),
                new Employee(111, "赵六", 8, 7777.77, DateHelper.parseString("2018-07-26 15:45:38")),
                new Employee(112, "田七", 38, 5555.55, DateHelper.parseString("2018-07-26 15:46:38"))
        );
        return  employeelist;
    }


    /**
     * 循环迭代模式(过滤器)
     */
    @Test
    public void Test1() {

        List<Employee> Employeelist=GetList();

        //第一种方式(不常用)
        /*Employeelist=Employeelist.stream().filter(p->{
            return  p.getAge()==8;
        }).collect(Collectors.toList());*/
        //第二种方式(常用)
        Employeelist=Employeelist.stream().filter(p->p.getAge()==8&&p.getSalary()==7777.77).collect(Collectors.toList());

        //第一种方式循环
        //stream.forEach(System.out::println);
        //迭代器循环
        Iterator<Employee> iterator = Employeelist.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }

    /**
     * 排序
     */
    @Test
    public void Test2() {
        //按时间正序排列
        //List<Employee> Employeelist=GetList().stream().sorted(Comparator.comparing(Employee::getDateTime)).collect(Collectors.toList());

        //按时间倒序排列
        List<Employee> Employeelist=GetList().stream().sorted(Comparator.comparing(Employee::getDateTime).reversed()).collect(Collectors.toList());

        Iterator<Employee> iterator = Employeelist.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }


    /**
     * 根据集合中的属性字段去重
     */
    @Test
    public void Test3() {
        //根据名字和属性去重
        List<Employee> Employeelist=GetList().stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(f -> f.getName()+f.getAge()))), ArrayList::new));


    }

    /**
     * 使用并行流计算(更有效率)
     */
    @Test
    public void Test4() {

        List<Employee> Employeelist1=GetList().stream().parallel().collect(Collectors.toList());

        List<Employee> Employeelist2=GetList().parallelStream().collect(Collectors.toList());

    }


    /**
     * 使用分页技术
     */
    @Test
    public void Test5() {
        int page=2;
        int pageSize=10;
        List<Employee> Employeelist=GetList().stream().parallel().sorted(Comparator.comparing(Employee::getDateTime).reversed()).skip((page-1)*pageSize).limit(pageSize).collect(Collectors.toList());

    }


    /**
     * 使用map映射
     */
    @Test
    public void Test6() {

        List<String> Employeelist=GetList().stream().parallel().map(p->p.getName()).collect(Collectors.toList());

    }


    /*
        allMatch——检查是否匹配所有元素
        anyMatch——检查是否至少匹配一个元素
        noneMatch——检查是否没有匹配的元素
        findFirst——返回第一个元素
        findAny——返回当前流中的任意元素
        count——返回流中元素的总个数
        max——返回流中最大值
        min——返回流中最小值
     */
    @Test
    public void Test7() {

        boolean flag1=GetList().stream().parallel().allMatch(p->p.getName().contains("张三"));

        boolean flag2=GetList().stream().parallel().anyMatch(p->p.getName().contains("张三"));

        boolean flag3=GetList().stream().parallel().noneMatch(p->p.getName().contains("张三"));

        Optional<Employee> model=GetList().stream().parallel().filter(p->p.getName().equals("张三")).findFirst();

        long count=GetList().stream().count();


    }


    /**
     * 分组计算
     */
    @Test
    public void Test8() {

        Map<String, List<Employee>> group = GetList().stream().collect(Collectors.groupingBy(Employee::getName));

    }


    /**
     * reduce 计算
     */
    @Test
    public void Test9() {

        //int类型的计算
        Integer sum = GetList().stream().map(p->p.getAge()).reduce(0, (x, y) -> x + y);
        System.out.println(sum);

        //double类型的计算
        Optional<Double> op = GetList().stream().map(Employee::getSalary).reduce(Double::sum);
        System.out.println(op.get());


    }





}
