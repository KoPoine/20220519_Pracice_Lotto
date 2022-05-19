package com.neppplus.a20220519_pracice_lotto

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

//    내 번호 6개 저장
    var mMyNumList = arrayOf(3, 5, 7, 8, 9, 11)

//    컴퓨터가 뽑은 당첨번호를 6개 저장할 ArrayList 만들어주자
    val mWinNumList = ArrayList<Int>()

//    랜덤 번호 6개를 집어넣을 텍스트뷰 자료형의 ArrayList를 만들자
    val mWinNumViewList = ArrayList<TextView>()

//    보너스 숫자 저장용 멤버변수 생성
    var mBonusNum = 0

//    사용금액 / 당첨금액 / 당첨 횟수
    var mUsedMoney = 0
    var mEaredMoney = 0L   // 30억 이상의 당첨 대비, Long 타입으로 설정

    var firstCount = 0
    var secondCount = 0
    var thirdCount = 0
    var fourthCount = 0
    var fifthCount = 0
    var loseCount = 0

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
//        3. 보너스 번호 하나 선정 // 텍스트 뷰에 배치
        while (true) {
            val randomNum = (Math.random() * 45 + 1).toInt()

            if (!mWinNumList.contains(randomNum)) {
                mBonusNum = randomNum
                bunusNumTxt.text = mBonusNum.toString()
                break
            }
        }
        checkLottoRank()
    }

    fun checkLottoRank() {
//        4. 비교
        var correctCount = 0

//        내 번호를 하나씩 조회
        for (myNum in mMyNumList) {

//            당첨 번호를 맞췄는가? => 당첨번호 목록에 내 번호가 들어있나?
            if (mWinNumList.contains(myNum)) {
                correctCount++
            }
        }
//        순위선정 (텍스트 뷰 출력)
        when (correctCount) {
            6 -> {
                mEaredMoney += 3000000000
                firstCount++
            }
            5 -> {
//                보너스 번호를 맞췃는지? => 보너스 번호가 내 번호 목록에 들어있나?
                if (mMyNumList.contains(mBonusNum)) {
                    mEaredMoney += 50000000
                    secondCount++
                }
                else {
                    mEaredMoney += 2000000
                    thirdCount ++
                }
            }
            4-> {
                mEaredMoney += 50000
                fourthCount++
            }
            3 -> {
                mEaredMoney += 5000
                fifthCount++
            }
            else -> {
                loseCount
            }
        }
//        사용금액 / 당첨 금액 및 횟수 텍스튜에 각각 반영
        usedMoneyTxt.text = "사용 금액 : ${NumberFormat.getInstance().format(mUsedMoney)}원"
        earnMoneyTxt.text = "${NumberFormat.getInstance().format(mEaredMoney)}원"

        winRank1Txt.text = "1등 당첨 횟수 : ${firstCount}회"
        winRank1Txt.text = "2등 당첨 횟수 : ${secondCount}회"
        winRank1Txt.text = "3등 당첨 횟수 : ${thirdCount}회"
        winRank1Txt.text = "4등 당첨 횟수 : ${fourthCount}회"
        winRank1Txt.text = "5등 당첨 횟수 : ${fifthCount}회"
        winRank1Txt.text = "낙첨 횟수 : ${loseCount}회"

    }
}















