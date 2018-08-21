package jenkins;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        newJob.class,
        deleteJob.class
})
public class AllSuite {
}
