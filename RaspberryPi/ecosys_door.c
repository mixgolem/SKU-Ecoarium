#include <stdio.h>    // 표준 입출력 함수 라이브러리 정의
#include <wiringPi.h> // GPIO access library 헤더파일 선언
#include <softPwm.h>  // Software PWM library 헤더파일 선언
#include <string.h>   // 문자열 비교를 위한 헤더파일 선언

#define SERVO1 15       // 상단
#define SERVO2 17       // 하단
#define PWM_CLOSED 15   // 닫혀있는 경우
#define PWM_OPENED_1 5  // 열려있는 경우
#define PWM_OPENED_2 25 // 열려있는 경우

int main(int argc, char *argv[])
{
    if (argc != 2)
    {
        fprintf(stderr, "Usage: %s <o|c>\n", argv[0]);
        return 1;
    }

    if (wiringPiSetupGpio() == -1) // wiringPi GPIO 사용하기 위한 설정
    {
        fprintf(stderr, "Failed to setup wiringPi\n");
        return 1;
    }

    softPwmCreate(SERVO1, 0, 100);
    softPwmCreate(SERVO2, 0, 100);

    int degree1 = 0;
    int degree2 = 0;

    if (strcmp(argv[1], "o") == 0) // open
    {
        fprintf(stdout, "\nOpening the door\n");
        degree1 = PWM_CLOSED;
        degree2 = PWM_CLOSED;
        softPwmWrite(SERVO1, degree1);
        softPwmWrite(SERVO2, degree2);

        for (int i = 0; i < 10; i++)
        {
            degree1 -= 1;
            degree2 += 1;
            softPwmWrite(SERVO1, degree1);
            softPwmWrite(SERVO2, degree2);
            delay(30);
        }
    }
    else if (strcmp(argv[1], "c") == 0) // close
    {
        fprintf(stdout, "\nClosing the door\n");
        degree1 = PWM_OPENED_1;
        degree2 = PWM_OPENED_2;
        softPwmWrite(SERVO1, degree1);
        softPwmWrite(SERVO2, degree2);

        for (int i = 0; i < 10; i++)
        {
            degree1 += 1;
            degree2 -= 1;
            softPwmWrite(SERVO1, degree1);
            softPwmWrite(SERVO2, degree2);
            delay(30);
        }
    }
    else
    {
        fprintf(stdout, "\nError: Invalid argument\n");
        return 1;
    }

    return 0;
}
