//import com.google.gson.Gson;
//import com.google.gson.annotations.SerializedName;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.List;
//
//public class FoodOutletDataFetcher {
//    public static void main(String[] args) {
//        String apiUrl = "https://jsonmock.hackerrank.com/api/food_outlets?city=Denver&page=1";
//
//        try {
//            // Create URL object
//            URL url = new URL(apiUrl);
//
//            // Open connection
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//
//            // Read the response
//            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            StringBuilder response = new StringBuilder();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                response.append(line);
//            }
//            reader.close();
//
//            // Deserialize the response using Gson
//            Gson gson = new Gson();
//            FoodOutletResponse foodOutletResponse = gson.fromJson(response.toString(), FoodOutletResponse.class);
//
//            // Access the deserialized data
//            List<FoodOutlet> foodOutlets = foodOutletResponse.data;
//            for (FoodOutlet outlet : foodOutlets) {
//                System.out.println(outlet);
//            }
//
//            // Disconnect the connection
//            connection.disconnect();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    static class FoodOutletResponse {
//        @SerializedName("data")
//        List<FoodOutlet> data;
//    }
//
//    static class FoodOutlet {
//        private String name;
//        private String city;
//        // Add more fields as needed
//
//        // Getters and setters
//
//        @Override
//        public String toString() {
//            return "FoodOutlet{" +
//                    "name='" + name + '\'' +
//                    ", city='" + city + '\'' +
//                    '}';
//        }
//    }
//}
