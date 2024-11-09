package com.example.planktonhackathon.domain.attraction.domain;

import com.example.planktonhackathon.domain.attraction.exception.AttractionErrorCode;
import com.example.planktonhackathon.global.exception.RestApiException;
import java.util.List;
import java.util.Random;

public enum Category {
    SHOPPING("쇼핑",
            "https://plankton-hackathon-s3.s3.ap-northeast-2.amazonaws.com/category/shopping.jpg",
            List.of(
                    "Shop at Traditional Markets in Seoul",
                    "Find unique items at Seoul's street markets",
                    "Discover hidden treasures while shopping in Seoul"
            ),
            List.of(
                    "Shop at Seoul’s traditional markets, browse various items, and take a photo of your shopping experience.",
                    "Take a photo of your shopping haul under 10,000 KRW",
                    "Capture a photo of your favorite item in the market.",
                    "Shop at Seoul’s traditional markets, browse various items, and take a photo of your shopping experience.",
                    "Explore vintage items at Seoul’s flea markets and capture a shot of the unique finds.",
                    "Buy unique souvenirs at Seoul’s souvenir shops and take a photo of your purchased items."
            )),
    CULTURE("문화관광",
            "https://plankton-hackathon-s3.s3.ap-northeast-2.amazonaws.com/category/culture.jpg",
            List.of(
                    "Visit a Vibrant Cultural Festival",
                    "Explore Seoul’s rich cultural heritage",
                    "Discover the beauty of Seoul’s cultural landmarks"
            ),
            List.of(
                    "Join a festival in Seoul and capture different cultural experiences in photos.",
                    "Take a photo of a cultural performance at the festival",
                    "Capture a snapshot of traditional Korean art.",
                    "Enjoy street performances in downtown Seoul and capture the joyful moments.",
                    "Visit an art gallery in Seoul and take a photo with your favorite artwork."
            )),
    SPORTS("레저스포츠",
            "https://plankton-hackathon-s3.s3.ap-northeast-2.amazonaws.com/category/sports.jpg",
            List.of(
                    "Experience Thrills at an Adventure Sports Park",
                    "Try different sports in Seoul",
                    "Enjoy a fun and active day at Seoul’s sports venues"
            ),
            List.of(
                    "Visit a leisure sports experience center in Seoul, try out different sports, and document the moments.",
                    "Take a photo while playing an active sport",
                    "Capture a moment of you in action during your favorite sport.",
                    "Ride a bike along the Han River! Take pictures with beautiful scenery along the way.",
                    "Challenge yourself with tricks at a skateboard park in Seoul, and capture the exciting moments with photos."
            )),
    HISTORY("역사관광",
            "https://plankton-hackathon-s3.s3.ap-northeast-2.amazonaws.com/category/history.jpg",
            List.of(
                    "Find the Hidden Treasures of Seoul’s History!",
                    "Walk Along the Old City Walls of Hanseong",
                    "Find Inner Peace at a Temple",
                    "Explore the Traditional Markets of Old Seoul",
                    "Discover at the History Museum"
            ),
            List.of(
                    "Walk along the Seoul City Wall and take photos at intervals to share! Feel the old Seoul while walking on the city wall.",
                    "Capture the ancient architecture of the Seoul City Wall",
                    "Take a photo with a historical monument in the background.",
                    "Visit a Buddhist temple in Seoul and experience some quiet healing. Share photos and thoughts capturing the serene moments at the temple.",
                    "Visit a traditional market and experience the old Seoul while capturing the vibrant market scene.",
                    "Visit a history museum in Seoul and find the most interesting artifact to share with a photo."
            )),
    NATURE("자연관광",
            "https://plankton-hackathon-s3.s3.ap-northeast-2.amazonaws.com/category/nature.jpg",
            List.of(
                    "Relax with the Sound of Water Along the River",
                    "Discover the beauty of nature in Seoul",
                    "Take in the natural scenery of Seoul’s parks and rivers"
            ),
            List.of(
                    "Walk along the river and document the peaceful moments.",
                    "Capture the serene beauty of Seoul’s green spaces",
                    "Take a photo of a calm river scene.",
                    "Admire the view of Seoul and capture the beautiful scenery.",
                    "Relax and Heal on a Leisurely Walking Path. Enjoy a leisurely walk on a trail and take a photo as a keepsake."
            )),
    ACTIVITY("체험관광",
            "https://plankton-hackathon-s3.s3.ap-northeast-2.amazonaws.com/category/activity.jpg",
            List.of(
                    "Try On a Hanbok (Traditional Korean Clothing)",
                    "Experience unique activities in Seoul",
                    "Create unforgettable memories through hands-on experiences"
            ),
            List.of(
                    "Wear a traditional hanbok and visit famous landmarks in Seoul, taking photos with your beautiful hanbok.",
                    "Capture your hanbok experience at Gyeongbokgung Palace",
                    "Take a photo of your hanbok with a cultural backdrop.",
                    "Try making traditional food yourself and capture the process and the final result in a photo.",
                    "Practice meditation at a temple and capture the peaceful moments."
            )),
    FOOD("음식",
            "https://plankton-hackathon-s3.s3.ap-northeast-2.amazonaws.com/category/food.jpg",
            List.of(
                    "Experience Traditional Korean Cuisine",
                    "Indulge in Seoul’s food culture",
                    "Discover delicious Korean dishes"
            ),
            List.of(
                    "Delight in traditional Korean dishes at a traditional Korean restaurant and capture photos of the meticulously prepared meals.",
                    "Take a photo of a steaming bowl of kimchi jjigae",
                    "Capture the vibrant colors of a Korean banchan (side dishes).",
                    "Enjoy delicious food at Seoul’s famous restaurants and snap a great photo to document your experience.",
                    "Find street food in Seoul, enjoy its special flavors, and take a photo to remember the taste."
            )),
    OTHER("기타관광",
            "https://plankton-hackathon-s3.s3.ap-northeast-2.amazonaws.com/category/other.jpg",
            List.of(
                    "Take Photos on Seoul’s Famous Streets",
                    "Explore the unique charm of Seoul’s neighborhoods",
                    "Discover hidden gems in Seoul’s vibrant streets"
            ),
            List.of(
                    "Take photos of the street scenery on Seoul’s famous streets and share the atmosphere of the city.",
                    "Capture the bustling energy of a busy Seoul street",
                    "Take a photo with a unique street art mural.",
                    "Explore trendy cafe culture and take a photo while enjoying coffee at a hip café in Seoul.",
                    "Tour Art Galleries in Seoul and take a photo with the exhibited works."
            ));

    private final String bigCategory;
    private final String imageURL;
    private final List<String> text;  // 여러 개의 text
    private final List<String> mission;  // 여러 개의 mission

    // 생성자
    Category(String bigCategory, String imageURL, List<String> text, List<String> mission) {
        this.bigCategory = bigCategory;
        this.imageURL = imageURL;
        this.text = text;
        this.mission = mission;
    }

    // Getter 메소드
    public String getBigCategory() {
        return bigCategory;
    }

    public String getImageURL() {
        return imageURL;
    }

    public List<String> getText() {
        return text;
    }

    public List<String> getMission() {
        return mission;
    }

    // 랜덤으로 하나의 텍스트와 미션을 뽑는 메소드
    public String getRandomText() {
        Random random = new Random();
        return text.get(random.nextInt(text.size()));
    }

    public String getRandomMission() {
        Random random = new Random();
        return mission.get(random.nextInt(mission.size()));
    }

    // 특정 bigCategory로 Enum을 찾는 메소드 (필요한 경우 사용)
    public static Category fromBigCategory(String bigCategory) {
        for (Category category : values()) {
            if (category.getBigCategory().equals(bigCategory)) {
                return category;
            }
        }
        throw new RestApiException(AttractionErrorCode.ATTRACTION_DOES_NOT_EXIST);
    }
}
