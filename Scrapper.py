from googlesearch import search, get_page
from bs4 import BeautifulSoup

import csv
import requests

# IO file names
input_file_name = 'IS_DATA.csv'
output_file_name = 'cluster_data_IS_output.csv'

# The depth limit for the crawler to crawl
depth_limit = 3

# Columns headers in the csv file
field1 = 'tran_to'
field2 = 'to_cluster_10000'
field3 = 'cluster'

# Tags to find in the webpages
categories = [
    'exchange', 'gambling', 'hosted wallet', 'merchant',
    'online shopping store', 'mining pools', 'mixing',
    'tor', 'ransomware', 'scam', 'smart contract',
    'bank', 'simple user'
]


# function to return a list of tags in the webpage content
def find_categories(content):
    content = content.strip().lower()
    return [category for category in categories if category in content]


# f untion to remove duplicate tags
def remove_duplicates(input):
    return set(input)


# function to scrape the contents of webpage
def get_site_content(url):
    html = requests.get(url)
    soup = BeautifulSoup(html.content, 'html.parser')
    return soup.get_text()

# function to return urls based on query
def search_url(query):
    try:
        s=search(query, pause=2.0, stop=depth_limit)
        return s
    except Exception as e:
        print(e)
        return ""

# function to search the urls containing the search term using google search api
def google_search(query):
    output = []
    for url in search_url(query):
        try:
            response = get_site_content(url)
        except Exception as e:
            print(str(e))
            continue
        output.extend(find_categories(response))

    return remove_duplicates(output) or {'other'}


# function to read records from the input file
def get_next_address():
    with open(input_file_name, newline='') as csv_file:
        csv_reader = csv.DictReader(csv_file)
        for row in csv_reader:
            address = row[field1]
            cluster = row[field2]
            yield address, cluster


def main():
    # opening the input file
    with open(output_file_name, 'w', newline='') as csv_file:
        fieldnames = [field1, field2, field3]
        csv_writer = csv.DictWriter(csv_file, fieldnames=fieldnames)
        csv_writer.writeheader()

        for address, cluster in get_next_address():
            # get relevant urls for each address
            results = google_search(address)
            # print results
            print(address, cluster, results)
            # write to output file
            csv_writer.writerow({
                field1: address,
                field2: cluster,
                field3: results,
            })


if __name__ == '__main__':
    main()
