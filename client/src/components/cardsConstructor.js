import img1 from './Ads/assets/img-1.jpg';
import { Link } from 'react-router-dom';
import { capitalize } from './helperFunctions';


export const cardsConstructor = (array) => {
  return (
    array.map((ad, index) => {
      return (
        <section key={ index } className="ad">

          <Link to={ `/${ ad.id }`}>
            <div className="cards__item">
              
              <div className="cards__info">
                <p className="cards__info-price">{ capitalize(ad.offer) }</p>
                <p className="cards__info-date">{ ad.offer === 'продажа' ? `${ad.price} сом/кв. метр` : `${ad.price} сом/мес` }</p>
              </div>

              <img src={img1} alt="Фотография популярных объявлений"
                className="cards__img" />

              <ul className="cards__description ">
                <li className="cards__description-text">{ capitalize(ad.type) }</li>
                &#183; 
                <li className="cards__description-text">Комнат: { ad.numberOfRooms }</li>
                &#183;
                <li className="cards__description-text">{ ad.area } кв.м</li>
              </ul>

              <div className="cards__location">
                <p className="cards__location-text">{ ad.city }, { ad.address }</p>
              </div>

            </div>   
          </Link>
        </section>      
      )
    }))
}