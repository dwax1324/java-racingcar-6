package racingcar;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;


    @Test
    @DisplayName("입력->이름->문자열 길이가 5를 초과함")
    void 이름_글자_수_예외_처리() {
        assertSimpleTest(() -> assertThatThrownBy(() -> runException("pobiqwrwr", "1")).isInstanceOf(
                IllegalArgumentException.class));
    }

    @Test
    @DisplayName("입력->이름->입력을 건너뜀")
    void 이름_글자_수_예외_처리2() {
        assertSimpleTest(() -> assertThatThrownBy(() -> runException("", "1")).isInstanceOf(
                IllegalArgumentException.class));
    }

    @Test
    @DisplayName("입력->이름->영어 소문자가 아님")
    void 영어_소문자가_아닌_문자열_예외_처리() {
        assertSimpleTest(() -> assertThatThrownBy(() -> runException(" ,wt, !", "1")).isInstanceOf(
                IllegalArgumentException.class));
    }

    @Test
    @DisplayName("입력->이름->연속된 구분자 포함")
    void 연속된_구분자에_대한_예외_처리() {
        assertSimpleTest(() -> assertThatThrownBy(() -> runException("qrr,,rr", "1")).isInstanceOf(
                IllegalArgumentException.class));
    }


    @Test
    @DisplayName("입력->이름->중복된 이름 포함")
    void 중복된_이름에_대한_예외_처리() {
        assertSimpleTest(() -> assertThatThrownBy(() -> runException("abc,abc", "1")).isInstanceOf(
                IllegalArgumentException.class));
    }

    @Test
    @DisplayName("입력->게임 횟수-> 숫자가 아닌 문자 포함")
    void 게임_횟수가_올바른_문자인지에_대한_예외_처리() {
        assertSimpleTest(() -> assertThatThrownBy(() -> runException("abc,def", "a")).isInstanceOf(
                IllegalArgumentException.class));
        assertSimpleTest(() -> assertThatThrownBy(() -> runException("abc,def", "-1")).isInstanceOf(
                IllegalArgumentException.class));
    }

    @Test
    @DisplayName("입력->게임 횟수-> 범위(1..1000)를 벗어남")
    void 게임_횟수가_범위_안에_있는지에_대한_예외_처리() {
        assertSimpleTest(() -> assertThatThrownBy(() -> runException("abc,def", "0")).isInstanceOf(
                IllegalArgumentException.class));
        assertSimpleTest(() -> assertThatThrownBy(() -> runException("abc,def", "1001")).isInstanceOf(
                IllegalArgumentException.class));
    }


    @Test
    void 전진_정지() {
        assertRandomNumberInRangeTest(() -> {
            run("pobi,woni", "1");
            assertThat(output()).contains("pobi : -", "woni : ", "최종 우승자 : pobi");
        }, MOVING_FORWARD, STOP);
    }

    @Test
    void 이름에_대한_예외_처리() {
        assertSimpleTest(() -> assertThatThrownBy(() -> runException("pobi,javaji", "1")).isInstanceOf(
                IllegalArgumentException.class));
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
