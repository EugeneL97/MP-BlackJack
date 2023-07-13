package testing;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)

@SuiteClasses({
	CardTesting.class, MessageTesting.class, PlayerTesting.class, RoomTesting.class, CardShoeTesting.class
})
public class AllTests {
}