import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<TikTokProfile> alltiktokprofiles = parseCSV("src/tiktok.profiles.csv");
        printTop10MostFollowedProfiles(alltiktokprofiles);
        printTop10LeastFollowedProfiles(alltiktokprofiles);
        printTotalVerifiedProfiles(alltiktokprofiles);
        printTop10LikesPerVideoProfiles(alltiktokprofiles);
    }

    private static void printTop10LikesPerVideoProfiles(ArrayList<TikTokProfile> alltiktokprofiles) {
        ArrayList<TikTokProfile> temp = new ArrayList<>(alltiktokprofiles);

        temp.sort((p1, p2) -> {
            return p2.getLikesPerVideo().compareTo(p1.getLikesPerVideo());
        });

        for (int i = 0; i < 10; i++) {
            System.out.println(temp.get(i));
        }
    }

    private static void printTotalVerifiedProfiles(ArrayList<TikTokProfile> alltiktokprofiles) {
        int totalVerifiedProfiles = 0;

        for (TikTokProfile p : alltiktokprofiles) {
            if (p.getIs_verified()) {
                totalVerifiedProfiles++;
            }
        }

        System.out.println("Total verified profiles: " + totalVerifiedProfiles);

    }

    private static void printTop10LeastFollowedProfiles(ArrayList<TikTokProfile> alltiktokprofiles) {
        ArrayList<TikTokProfile> temp = new ArrayList<>(alltiktokprofiles);

        Collections.sort(temp);

        for (int i = 0; i < 10; i++) {
            System.out.println(temp.get(i));
        }
    }

    private static void printTop10MostFollowedProfiles(ArrayList<TikTokProfile> alltiktokprofiles) {
        ArrayList<TikTokProfile> temp = new ArrayList<>(alltiktokprofiles);

//        Collections.sort(temp);
//        Collections.reverse(temp);

        temp.sort((p1, p2) -> {
            return p2.getFollowers().compareTo(p1.getFollowers());
        });

        for (int i = 0; i < 10; i++) {
            System.out.println(temp.get(i));
        }
    }

    public static ArrayList<TikTokProfile> parseCSV(String filename) {
        ArrayList<TikTokProfile> tiktokprofiles = null;
        try {
            //declare the file to be parsed
            File file = new File(filename);

            //read from the file using Scanner
            Scanner scanner = new Scanner(file);

            //ArrayList to hold TikTok profiles
            tiktokprofiles = new ArrayList<>();

            //read the file
            //first, skip the header
            if (scanner.hasNextLine()) scanner.nextLine();

            while (scanner.hasNextLine()) {
                //save the line in a variable
                String line = scanner.nextLine();

                //split by ,
                String[] elements = line.split(",");

                //extract all variables to create a TikTokProfile
                String account_id = elements[0];
                double avg_eng_rate = Double.parseDouble(elements[1]);
                boolean is_verified = Boolean.parseBoolean(elements[2]);
                int followers = Integer.parseInt(elements[3]);
                int following = Integer.parseInt(elements[4]);
                int likes = Integer.parseInt(elements[5]);
                int videos_count = Integer.parseInt(elements[6]);

                TikTokProfile p = new TikTokProfile(
                        account_id,
                        avg_eng_rate,
                        is_verified,
                        followers,
                        following,
                        likes,
                        videos_count
                );

                tiktokprofiles.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tiktokprofiles;
    }
}