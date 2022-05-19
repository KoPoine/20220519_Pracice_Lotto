package com.neppplus.a20220519_pracice_lotto

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

//    컴퓨터가 뽑은 당첨번호를 6개 저장할 ArrayList 만들어주자
    val mWinNumList = ArrayList<Int>()

//    랜덤 번호 6개를 집어넣을 텍스트뷰 자료형의 ArrayList를 만들자
    val mWinNumViewList = ArrayList<TextView>()

//    보너스 숫자 저장용 멤버변수 생성
    var bonusNum = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }

    fun setupEvents() {
        buyBtn.setOnClickListener {
//            로또 한장 구매 버튼 눌렀을때의 로직 진행
            buyLotto()
        }
    }

    fun setValues() {

        mWinNumViewList.add(winNum1Txt)
        mWinNumViewList.add(winNum2Txt)
        mWinNumViewList.add(winNum3Txt)
        mWinNumViewList.add(winNum4Txt)
        mWinNumViewList.add(winNum5Txt)
        mWinNumViewList.add(winNum6Txt)
    }

    fun buyLotto() {

//        ArrayList는 목록을 계속 누적 가능
//        당첨 번호 새로 뽑기전에 기존의 당첨번호는 전부 삭제하고 다시 뽑자
        mWinNumList.clear()

//        1. 로또 당첨 번호 6개 선정
        for (i in 0 until 6) {

//            괜찮은(중복되지 않은) 번호가 나올때까지 무한 반복
            while (true) {
                val randomNum = (Math.random() * 45 + 1).toInt()

//                중복 검사시 while문을 break
                if (!mWinNumList.contains(randomNum)) {
//                    당첨번호로 뽑은 랜덤 숫자 등록
                    mWinNumList.add(randomNum)
                    break
                }
            }
        }

//        2. 당첨 번호 정렬 (sort) -> 작은수 ~ 큰수 -> 텍스트뷰에 표현
        mWinNumList.sort()

//        for문을 돌면서, 당첨번호도 텍스트뷰에 배치 / 어느텍스트 뷰인지 찾기 위해서 withIndex 라는 것을 활용
//        mWinNumList.forEachIndexed { index, winNum ->
//            mWinNumViewList[index].text = winNum.toString()
//        }

        for ((index, winNum) in mWinNumList.withIndex()) {
            mWinNumViewList[index].text = winNum.toString()
        }
//        3. 보너스 번호 하나 선정




    }

    fun checkLottoRank() {
//        4. 비교


//        순위선정 (텍스트 뷰 출력)



    }
}