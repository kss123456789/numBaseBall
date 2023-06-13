// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TeamWork1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numlen = 3;
        String answer = randNums(numlen);
//        System.out.println(answer);
        System.out.println("컴퓨터가 숫자를 생성하였습니다. 답을 맞춰보세요!");
        System.out.println("중복없이 " + numlen + "개의 숫자만 입력해주세요.");
        int ball; //contain
        int strike; //charAt, indexOf
        int count = 0;
        while (true) {
            String input = br.readLine();
            // 입력시 에러가 생길 수 있는 값들 처리
            try {
                Integer.parseInt(input);
                input.charAt(numlen-1);
                if (input.length() > numlen) {
                    RuntimeException e3 = new RuntimeException(numlen + "개 이상의 숫자");
                    throw e3;
                }
                if (hasDuplicateCharacters(input)) {
                    RuntimeException e4 = new RuntimeException("중복인 숫자");
                    throw e4;
                }
            } catch (NumberFormatException e1) {
                System.out.println("숫자가 아닙니다 : " + e1.getMessage());
                continue;
            } catch (StringIndexOutOfBoundsException e2) {
                System.out.println("숫자가 부족합니다 : " + e2.getMessage());
                continue;
            } catch (RuntimeException e3) {
                System.out.println("중복없이 " + numlen + "개의 숫자만 입력해주세요 : " + e3.getMessage());
                continue;
            }
            ball = 0;
            strike = 0;
            StringBuilder sb = new StringBuilder();
            count++;
            sb.append(count).append("번째 시도 : ").append(input).append("\n");
            // 입력한 값과 정답을 비교하여 strike, ball 증가
            for (int i = 0; i < 3; i++) {
                String num = "" + input.charAt(i);
                if (answer.contains(num)) {
                    if (answer.indexOf(num) == i) {
                        strike++;
                    } else {
                        ball++;
                    }
                }
            }
            // strike 또는 ball만 있는경우
            if (ball == 3) {
                sb.append(ball).append("B");
            } else if (strike == 3) {
                sb.append(strike).append("S").append("\n");
                sb.append(count).append("번만에 맞히셨습니다.\n게임을 종료합니다.");
                System.out.println(sb);
                return;
            } else {
                sb.append(ball).append("B").append(strike).append("S");
            }
            System.out.println(sb);
        }
    }
// 0에서 9까지의 랜덤한 숫자를 일정 갯수만큼 생성
    private static String randNums(int numlen) {
        StringBuilder sb = new StringBuilder();
        int randNum;
        for (int i = 0; i < numlen; i++) {
            while (true) {
                randNum = (int) (Math.random() * 9);
                if (!sb.toString().contains("" + randNum)) {
                    break;
                }
            }
            sb.append(randNum);
        }
        return sb.toString();
    }
// 주어진 string에 중복되는 character가 있는지 검사
    public static boolean hasDuplicateCharacters(String str) {
        StringBuilder sb = new StringBuilder();

        for (char c : str.toCharArray()) {
            if (sb.toString().contains(""+c)) {
                // Duplicate character found
                return true;
            }
            sb.append(c);
        }
        // No duplicate characters found
        return false;
    }
}