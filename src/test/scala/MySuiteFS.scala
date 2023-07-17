import weaver.FunSuite

object MySuiteFS extends FunSuite {

  test("Test with fun suite") {
    expect(1 == 1)
  }

  test("Assert on boolean value") {
    val list = List(1, 2, 3, 4)
    val myVar = 25
    expect(myVar == 25 && list.size == 4)
  }

  test("compose expectation using and/or, &&/||") {
    expect(1 == 1) and expect(2 > 1) or expect(5 == 5)
    expect(1 == 1) && expect(2 > 1) || expect(5 == 5)
  }

  test("asserting on all boolean values") {
    expect.all(1 == 1, 2 == 2, 3 > 2)
  }

  test("use foreach to test every element of a collection") {
    forEach(List(1, 2, 3))(x => expect(x < 5))
  }

  test("use exists to assert that at least one element of collection matches expectations") {
    exists(Option(5))(n => expect(n > 3))
    exists(List("hello", "world"))(elem => expect.eql("hello", elem))
    exists(List(1, 2, 3))(elem => expect(elem == 3))
  }

  test("use matches to assert that an expression matches a given pattern") {
    matches(Option(4)) { case Some(x) => expect.eql(4, x) }
  }

  test("use whenSuccess to assert that a ApplicativeError(like Either) is successful") {
    val res: Either[String, Int] = Right(3)
    whenSuccess(res) {
      n => expect.eql(3, n)
    }
  }

  test("use expect.eql for strict comparison (types that implement Eq typeclass)") {
    expect.eql(List(1, 2, 3), (1 to 3).toList)
    expect.eql("Hello", "Hello")
  }

  test("use success and failure") {
    if (5 == 5) success else failure("oh no")
  }


}
