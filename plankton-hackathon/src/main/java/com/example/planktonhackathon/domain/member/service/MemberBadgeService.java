package com.example.planktonhackathon.domain.member.service;


import com.example.planktonhackathon.domain.member.domain.Member;
import com.example.planktonhackathon.domain.member.domain.MemberBadge;
import com.example.planktonhackathon.domain.member.repository.MemberBadgeRepository;
import com.example.planktonhackathon.domain.member.repository.MemberRepository;
import com.example.planktonhackathon.domain.member.response.MemberBadgeResponse;
import com.example.planktonhackathon.global.auth.exception.AuthErrorCode;
import com.example.planktonhackathon.global.auth.service.AuthMemberService;
import com.example.planktonhackathon.global.exception.RestApiException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberBadgeService {

    private final MemberRepository memberRepository;
    private final AuthMemberService authMemberService;
    private final MemberBadgeRepository memberBadgeRepository;

    private static final Map<String, String> KOREAN_TO_ENGLISH_DISTRICT_MAP = Map.ofEntries(
            Map.entry("강남구", "Gangnam"),
            Map.entry("강동구", "Gangdong"),
            Map.entry("강북구", "Gangbuk"),
            Map.entry("강서구", "Gangseo"),
            Map.entry("관악구", "Gwanak"),
            Map.entry("광진구", "Gwangjin"),
            Map.entry("구로구", "Guro"),
            Map.entry("금천구", "Geumcheon"),
            Map.entry("노원구", "Nowon"),
            Map.entry("도봉구", "Dobong"),
            Map.entry("동대문구", "Dongdaemun"),
            Map.entry("동작구", "Dongjak"),
            Map.entry("마포구", "Mapo"),
            Map.entry("서대문구", "Seodaemun"),
            Map.entry("서초구", "Seocho"),
            Map.entry("성동구", "Seongdong"),
            Map.entry("성북구", "Seongbuk"),
            Map.entry("송파구", "Songpa"),
            Map.entry("양천구", "Yangcheon"),
            Map.entry("영등포구", "Yeongdeungpo"),
            Map.entry("용산구", "Yongsan"),
            Map.entry("은평구", "Eunpyeong"),
            Map.entry("종로구", "Jongno"),
            Map.entry("중구", "Jung"),
            Map.entry("중랑구", "Jungnang")
    );

    private static final Map<String, String> CHAMPION = Map.ofEntries(
            Map.entry("챔피언", "Champion"),
            Map.entry("챔피언 후보", "Champion Candidate")
    );

    // 이미지 URL 생성 메서드
    private String getImageUrl(String district) {
        // 한글 district가 들어오면 영어로 변환
        String englishDistrict = KOREAN_TO_ENGLISH_DISTRICT_MAP.get(district);

        // 서울 전체를 위한 이미지 URL
        if ("서울".equalsIgnoreCase(district)) {
            return "https://plankton-hackathon-s3.s3.ap-northeast-2.amazonaws.com/images/master.png";
        }
        // 구에 대한 이미지 URL, 영어로 변환된 district 사용
        else if (englishDistrict != null) {
            return "https://plankton-hackathon-s3.s3.ap-northeast-2.amazonaws.com/images/seoul.png";
        }
        englishDistrict = CHAMPION.get(district);
        if(englishDistrict.equals("Champion")){
            return "https://plankton-hackathon-s3.s3.ap-northeast-2.amazonaws.com/images/Champion.png";
        }

        return "https://plankton-hackathon-s3.s3.ap-northeast-2.amazonaws.com/images/Champion+Candidate.png";
    }

    // email과 district를 기준으로 모든 멤버의 badge와 imageUrl 반환
    public List<MemberBadgeResponse> getMemberBadgeResponses() {
        Member member = memberRepository.findByEmail(authMemberService.getMemberEmail())
                .orElseThrow(() -> new RestApiException(AuthErrorCode.NO_USER_INFO));
        List<MemberBadge> memberBadges = memberBadgeRepository.findAllByEmail(member.getEmail());

        return memberBadges.stream()
                .map(memberBadge -> {
                    // district가 한글로 들어오면 영어로 변환
                    String badge = memberBadge.getDistrict();
                    String imageUrl = getImageUrl(badge);
                    String englishDistrict = KOREAN_TO_ENGLISH_DISTRICT_MAP.get(badge);

                    // 영어로 변환된 district 사용, 없으면 그대로 한글
                    if (englishDistrict != null) {
                        badge = englishDistrict;
                        badge = badge + " master";
                    }else{
                        badge = CHAMPION.get(badge);
                    }


                    // 이미지 URL 생성

                    return new MemberBadgeResponse(badge, imageUrl);
                })
                .collect(Collectors.toList());
    }
}
