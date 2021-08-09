import java.util.*;
import java.util.regex.*;

public class 매칭점수_regex {
    
    class Solution {
        
        List<String> urls;
        Map<String, Link> links;
        Pattern urlPattern, bodyPattern, hrefPattern;
        Matcher urlMatcher, bodyMatcher, hrefMatcher;
        
        class Link {
            String url;
            int basicScore;
            double matchingScore, linkScore;
            String[] extUrls;
            
            Link(String url) {
                this.url = url;
            }
            
            public void setExtUrls(List<String> urls) {
                this.extUrls = new String[urls.size()];
                for(int i=0; i<urls.size(); i++) {
                    extUrls[i] = urls.get(i);
                }
            }
        }
        
        public void setPattern(String word) {
            urlPattern = Pattern.compile("<meta property=\"og:url\" content=\"https://(\\S*)\"");
            bodyPattern = Pattern.compile("\\b(?i)" + word + "\\b");
            hrefPattern = Pattern.compile("<a href=\"https://(\\S*)\"");
        }
        
        public String getUrl(String page) {
            urlMatcher = urlPattern.matcher(page);
            urlMatcher.find();
            return urlMatcher.group().split("=")[2].replaceAll("\"", "");
        }
        
        public void getExternalUrls(String page, List<String> extUrls) {
            hrefMatcher = hrefPattern.matcher(page);
            
            while(hrefMatcher.find()) {
                extUrls.add(hrefMatcher.group().split("\"")[1]);
            }
        }
        
        public int getSameWordInPage(String page) {
            page = page.split("<body>")[1].replaceAll("[0-9]", " ");
            bodyMatcher = bodyPattern.matcher(page);
            int count = 0;
            
            while(bodyMatcher.find()) {
                count += 1;    
            }
            
            return count;
        }
        
        public void parsingPages(String word, String[] pages) {
            for(int i=0; i<pages.length; i++) {
                String url = getUrl(pages[i]);
                Link link = new Link(url);
                urls.add(url);
                
                List<String> extUrls = new ArrayList<>();
                getExternalUrls(pages[i], extUrls);
                link.setExtUrls(extUrls);
                
                link.basicScore = getSameWordInPage(pages[i]);
                link.linkScore = (double) link.basicScore/extUrls.size();
                    
                links.put(url, link);
            }
        }
        
        public int getMaxMatchingScore() {
            double maxScore=0, tempScore=0;
            int idx = 0;
            
            for(Link link: links.values()) {
                for(String url: link.extUrls) {
                    if(links.containsKey(url)) {
                        links.get(url).matchingScore += link.linkScore;    
                    }
                }
            }
            
            for(int i=0; i<urls.size(); i++) {
                Link link = links.get(urls.get(i));
                tempScore = link.basicScore + link.matchingScore;
                
                if(maxScore < tempScore) {
                    maxScore = tempScore;
                    idx = i;
                }
            }
            
            return idx;
        }
        
        public int solution(String word, String[] pages) {
            int answer = 0;
            urls = new ArrayList<>();
            links = new HashMap<>();
            setPattern(word);
            parsingPages(word, pages);
            answer = getMaxMatchingScore();
            
            return answer;
        }
    }
}
