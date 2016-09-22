

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


//全部单元测试集合，执行顺序如下
@RunWith(Suite.class)
@SuiteClasses({DTQQWebLogin.class, DTSinaWebLogin.class,
		PromtUserLogin.class, DTTaobaoWebLogin.class, DTDoubanWebLogin.class })
public class AllDTLoginTests {

}
